package com.example.androidresourcesdemo

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidresourcesdemo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar.apply {
            title = AnimationActivity::class.java.simpleName
        }

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation)

        binding.propertyAnimatorButton.setOnClickListener {
            (AnimatorInflater.loadAnimator(this, R.animator.property_animator) as AnimatorSet)
                .apply {
                    setTarget(binding.myTextView)
                    start()
                }
        }

        binding.valueAnimatorButton.setOnClickListener {
            (AnimatorInflater.loadAnimator(this, R.animator.value_animator) as ValueAnimator)
                .apply {
                    addUpdateListener { updatedAnimation ->
                        binding.myTextView.setBackgroundColor(updatedAnimation.animatedValue as Int)
                    }
                    start()
                }
        }

        binding.tweenAnimatorButton.setOnClickListener {
            binding.droidImageView.apply {
                // reset the content and background of the ImageView if the
                // frameAnimatorButton was clicked
                background?.let {
                    setImageResource(R.drawable.ic_baseline_android_24)
                    setBackgroundResource(0)
                }
                val hyperSpaceJump = AnimationUtils.loadAnimation(this@AnimationActivity, R.anim.hyperspace_jump)
                binding.droidImageView.startAnimation(hyperSpaceJump)
            }
        }

        binding.frameAnimatorButton.setOnClickListener {
            binding.droidImageView.apply {
                setImageResource(0)
                setBackgroundResource(R.drawable.moon_phase_frame_anim)
                background.let {
                    if (it is Animatable) it.start()
                }
            }
        }
    }
}