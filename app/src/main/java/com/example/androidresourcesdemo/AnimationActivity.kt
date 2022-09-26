package com.example.androidresourcesdemo

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidresourcesdemo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}