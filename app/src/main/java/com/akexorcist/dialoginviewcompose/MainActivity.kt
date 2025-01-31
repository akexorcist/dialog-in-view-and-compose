package com.akexorcist.dialoginviewcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.akexorcist.dialoginviewcompose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonView.setOnClickListener {
            ViewDialogFragment().show(supportFragmentManager, null)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(binding.fragmentContainerView.id, ComposeDialogFragment())
            }
        }
    }
}
