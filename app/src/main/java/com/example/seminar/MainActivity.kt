package com.example.seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.seminar.databinding.ActivityMainBinding
import com.example.seminar.db.ItemDao
import com.example.seminar.db.ItemDatabase
import com.example.seminar.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: ItemDao
    private val viewModel : MainViewModel by viewModels { MainViewModel.factory(dao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dao = ItemDatabase.getInstance(this).dao()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            MainLoadAdapter()
        )

        lifecycleScope.launch {
            viewModel.data.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}