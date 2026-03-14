package com.andforce.andclaw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.andforce.andclaw.databinding.ActivityChatHistoryBinding
import com.andforce.andclaw.view.ChatAdapter
import kotlinx.coroutines.launch

class ChatHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatHistoryBinding
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChatList()
        observeMessages()
    }

    private fun setupChatList() {
        chatAdapter = ChatAdapter { action -> AgentController.performConfirmedAction(action) }
        binding.chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatHistoryActivity)
            adapter = chatAdapter
        }
    }

    private fun observeMessages() {
        lifecycleScope.launch {
            AgentController.messages.collect { messageList ->
                chatAdapter.submitList(messageList)
                if (messageList.isNotEmpty()) {
                    binding.chatRecyclerView.smoothScrollToPosition(messageList.size - 1)
                }
            }
        }
    }
}
