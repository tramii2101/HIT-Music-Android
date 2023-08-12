package com.example.hitmusicapp.screen.play

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityPlayBinding
import com.example.hitmusicapp.entities.Song
import com.example.hitmusicapp.entity.SongResponse
import com.example.hitmusicapp.viewmodel.HomeViewModel
import com.example.hitmusicapp.viewmodel.SongViewModel
import java.util.concurrent.TimeUnit

class PlayActivity : BaseActivity<ActivityPlayBinding>() {

    val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    val songViewModel by lazy {
        ViewModelProvider(this)[SongViewModel::class.java]
    }

    private var mediaPlayer: MediaPlayer? = null


    val sharedPref by lazy {
        getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    var listMusic = mutableListOf<Song>()

    private var handler: Handler = Handler()

    override fun initData() {
        val bundle = intent.extras

        val token = sharedPref.getString("accessToken", "")

        when (val fragment = sharedPref.getString("fragmentSendData", "")) {
            "HomeFragment" -> {
                if (token != null) {
                    homeViewModel.accessToken = "Bearer $token"
                }
                homeViewModel.songPosition.value = bundle?.getInt("Song_position")

                homeViewModel.getSongAtHome()
                homeViewModel.listSong.observe(this) {
                    listMusic = it
                    homeViewModel.songPosition.observe(this) {
                        songViewModel.songId.value = listMusic[homeViewModel.songPosition.value!!].id.toString()
                    }
                }
            }

            else -> {
                if (token != null) {
                    songViewModel.accessToken = "Bearer $token"
                }
                when (fragment) {
                    "SingerDetail" -> {
                        songViewModel.singerId = bundle?.getString("Singer_id").toString()
                        songViewModel.songPosition.value = bundle?.getInt("Song_of_singer_position")
                        songViewModel.getSongBySinger()
                        songViewModel.listSongByCategory.observe(this) {
                            listMusic = it
                        }
                    }
                    "Category" -> {
                        songViewModel.categoryId = bundle?.getString("Category_id").toString()
                        songViewModel.songPosition.value = bundle?.getInt("Song_of_category_category")
                        songViewModel.getSongByCategory()
                        songViewModel.listSong.observe(this) {
                            listMusic = it
                        }
                    }
                }
                songViewModel.songPosition.observe(this) {
                    songViewModel.songId.value = listMusic[songViewModel.songPosition.value!!].id.toString()
                }
            }
        }

        songViewModel.songId.observe(this) {
            songViewModel.getSong()
        }

        songViewModel.song.observe(this) {
            if (it != null) {
                bindSingerData(it)
                mediaPlayer?.apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(it.audio)
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
                mediaPlayer?.start()
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
                    if (mediaPlayer?.isPlaying == true) {
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
            binding.seekBar.progress = (mediaPlayer?.currentPosition)?.minus(10000)!!
            mediaPlayer?.seekTo(binding.seekBar.progress)
        }

        binding.forward10.setOnClickListener {
            binding.seekBar.progress = (mediaPlayer?.currentPosition)?.plus(10000)!!
            mediaPlayer?.seekTo(binding.seekBar.progress)
        }

        binding.skipNext.setOnClickListener {
            Log.e("TAG", "initListener: baudcjuan", )
//            clearMediaPlayer()
            when (sharedPref.getString("fragmentSendData", "")) {
                "HomeFragment" -> {
                    homeViewModel.songPosition.value = homeViewModel.songPosition.value?.plus(1)
                }
                else -> {
                    songViewModel.songPosition.value = songViewModel.songPosition.value?.plus(1)
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
        mediaPlayer?.reset()
        mediaPlayer = null
//        mediaPlayer.release()



    }
    override fun onDestroy() {
        clearMediaPlayer()
        super.onDestroy()
    }

    override fun onBackPressed() {
        clearMediaPlayer()
        super.onBackPressed()
    }

    fun playMusicAtCurrentTime() {

    }


}