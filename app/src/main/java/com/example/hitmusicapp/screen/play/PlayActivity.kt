package com.example.hitmusicapp.screen.play

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Handler
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityPlayBinding
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.SongResponse
import com.example.hitmusicapp.entity.SongResultSearch
import com.example.hitmusicapp.viewmodel.HomeViewModel
import com.example.hitmusicapp.viewmodel.SongViewModel
import java.util.concurrent.TimeUnit

class PlayActivity : BaseActivity<ActivityPlayBinding>() {
    val songViewModel by lazy {
        ViewModelProvider(this)[SongViewModel::class.java]
    }

    val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

//    private val mediaPlayer by lazy {
//        MediaPlayer()
//    }

    private var mediaPlayer: MediaPlayer? = null

    val sharedPref by lazy {
        getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    //    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    private var thisSong : Song? = null

    private var songResult : SongResultSearch? = null

    override fun initData() {
        val bundle = intent.extras
        val token = sharedPref.getString("accessToken", "")
        val fragment = sharedPref.getString("fragmentSendData","")
        val post = bundle?.getInt("Song_position")

//        val receivedData = bundle?.getString("Song_id")
//        if (receivedData != null) {
//            songViewModel.songID = receivedData
//        }
        songViewModel.accessToken = "Bearer $token"

        when (fragment) {
            "HomeFragment" -> {
                homeViewModel.accessToken = "Bearer $token"
                homeViewModel.getSongAtHome()
                homeViewModel.songPosition.value = post
                homeViewModel.listSong.observe(this) {
                    if (homeViewModel.listSong.value?.size != 0) {
                        homeViewModel.songPosition.observe(this) {
                            thisSong = homeViewModel.songPosition.value?.let { it1 ->
                                homeViewModel.listSong.value?.get(
                                    it1
                                )
                            }
                            songViewModel.songID = thisSong?.id.toString()
                            songViewModel.getSong()
                            songViewModel.song.observe(this) {
                                if (it != null) {
                                    bindSingerData(it)
                                    it.audio?.let { it1 -> fillData(it1) }
                                    mediaPlayer!!.start()
                                }
                            }
                        }
                    }
                }
            }
            "SingerDetailFragment" -> {
                val singerId = bundle?.getString("Singer_id")
                songViewModel.singerId = singerId.toString()
                songViewModel.getSongBySinger()
                songViewModel.songPosition.value = post

                songViewModel.listSong.observe(this) {
                    if (songViewModel.listSong.value?.size != 0) {
                        thisSong = songViewModel.songPosition.value?.let { it1 ->
                            songViewModel.listSong.value?.get(
                                it1
                            )
                        }
                    }
                }
                songViewModel.songID = thisSong?.id.toString()
                songViewModel.getSong()
                songViewModel.song.observe(this) {
                    if (it != null) {
                        bindSingerData(it)
                        it.audio?.let { it1 -> fillData(it1) }
                        mediaPlayer!!.start()
                    }
                }
            }
            "CategoryFragment" -> {
                val categoryId = bundle?.getString("Category_id")
                songViewModel.categoryId = categoryId.toString()
                songViewModel.getSongByCategory()
                songViewModel.songPosition.value = post

                songViewModel.listSongByCategory.observe(this) {
                    if (songViewModel.listSongByCategory.value?.size != 0) {
                        thisSong = songViewModel.songPosition.value?.let { it1 ->
                            songViewModel.listSongByCategory.value?.get(
                                it1
                            )
                        }
                    }
                }
                songViewModel.songID = thisSong?.id.toString()
                songViewModel.getSong()
                songViewModel.song.observe(this) {
                    if (it != null) {
                        bindSingerData(it)
                        it.audio?.let { it1 -> fillData(it1) }
                        mediaPlayer!!.start()
                    }
                }
            }
            "ExploreFragment" -> {
                val keyword = bundle?.getString("keyword")
                homeViewModel.songPosition.value = post
                homeViewModel.keyword = keyword.toString()
                homeViewModel.getSearchResult()
                homeViewModel.data.observe(this) {
                    if (homeViewModel.data.value?.listSong?.size != 0) {
                        homeViewModel.songPosition.observe(this) {
                            songResult = homeViewModel.songPosition.value?.let { it1 ->
                                homeViewModel.data.value?.listSong?.get(
                                    it1
                                )
                            }
                            binding.tvSong.text = songResult?.title
                            binding.tvSinger.text = songResult?.singer?.fullname
                            Glide.with(binding.imgSong.context).load(songResult?.image).into(binding.imgSong)
                            songResult?.audio?.let { it1 -> fillData(it1) }
                            mediaPlayer!!.start()
                        }
                    }
                }
            }
        }
    }

    override fun initView() {

    }

    override fun initListener() {

        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mediaPlayer?.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    binding.currentTime.text = "0:00"
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer!!.seekTo(binding.seekBar.progress)
                    }
                }
            }
        )

        mediaPlayer?.setOnCompletionListener {
            binding.play.isChecked = false
            mediaPlayer!!.seekTo(0)
        }


        binding.play.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked)
                mediaPlayer?.pause()
            else mediaPlayer?.start()
        }

        binding.replay10.setOnClickListener {
            binding.seekBar.progress = (mediaPlayer?.currentPosition )?.minus(10000)!!
            mediaPlayer!!.seekTo(binding.seekBar.progress)
        }

        binding.forward10.setOnClickListener {
            binding.seekBar.progress = (mediaPlayer?.currentPosition )?.plus(10000)!!
            mediaPlayer!!.seekTo(binding.seekBar.progress)
        }

        val fragment = sharedPref.getString("fragmentSendData","")
        var size = 0

        when (fragment) {
            "HomeFragment" -> {
                size = homeViewModel.listSong.value?.size!!
            }

            "ExploreFragment" -> {
                size = homeViewModel.listSong.value?.size!!
            }
            "SingerDetailFragment" -> {
                size = songViewModel.listSong.value?.size!!
            }
            "CategoryFragment" -> {
                size = songViewModel.listSongByCategory.value?.size!!
            }
        }

        binding.skipNext.setOnClickListener {
            clearMediaPlayer()
            when (fragment) {
                "HomeFragment", "ExploreFragment" -> {
                    if (homeViewModel.songPosition.value!! < size)
                        homeViewModel.songPosition.value = homeViewModel.songPosition.value?.plus(1)
                }
                "SingerDetailFragment", "CategoryFragment" -> {
                    if (songViewModel.songPosition.value!! < size)
                        songViewModel.songPosition.value = homeViewModel.songPosition.value?.plus(1)
                }
            }

        }

        binding.skipPrevious.setOnClickListener {
            clearMediaPlayer()
            when (fragment) {
                "HomeFragment", "ExploreFragment" -> {
                    if (homeViewModel.songPosition.value!! < size)
                        homeViewModel.songPosition.value = homeViewModel.songPosition.value?.minus(1)
                }
                "SingerDetailFragment", "CategoryFragment" -> {
                    if (songViewModel.songPosition.value!! < size)
                        songViewModel.songPosition.value = homeViewModel.songPosition.value?.minus(1)
                }
            }
        }
    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityPlayBinding {
        return ActivityPlayBinding.inflate(layoutInflater)
    }


    fun bindSingerData(song: SongResponse) {
        Glide.with(applicationContext).load(song.image).into(binding.imgSong)
        binding.tvSinger.text = song.singer?.fullname
        binding.tvSong.text = song.title
    }

    fun timerConversion(value: Int): String {
        var audioTime = ""
        val hrs = value / 3600000
        val mns = value / 60000 % 60000
        val scs = value % 60000 / 1000

        audioTime = if (hrs > 0) {
            String.format("%02d:%02d:%02d", hrs, mns, scs)
        } else {
            String.format("%02d:%02d", mns, scs)
        }
        return audioTime
    }

    private fun clearMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
    override fun onDestroy() {
        super.onDestroy()
        clearMediaPlayer()
    }

    fun fillData(link: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(link)
            prepare()
        }?.setOnPreparedListener { media ->
            binding.seekBar.max = media.duration
            handler.post(object : Runnable {
                override fun run() {
                    val curDuration = mediaPlayer?.currentPosition?.toLong()
                    if (curDuration != null) {
                        val time = String.format(
                            "%02d:%02d ",
                            TimeUnit.MILLISECONDS.toMinutes(curDuration),
                            TimeUnit.MILLISECONDS.toSeconds(curDuration) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    curDuration
                                )
                            )
                        )
                        binding.totalTime.text = timerConversion(mediaPlayer!!.duration)
                        binding.currentTime.text = time
                        binding.seekBar.progress = curDuration.toInt()
                    }
                    handler.postDelayed(this, 1000)
                }
            })
        }
    }

}