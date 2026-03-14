package com.andforce.andclaw.view

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andforce.andclaw.R
import com.andforce.andclaw.databinding.ItemChatBubbleBinding
import com.andforce.andclaw.model.AiAction
import com.andforce.andclaw.model.ChatMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatAdapter(
    private val onConfirmAction: (AiAction) -> Unit
) : ListAdapter<ChatMessage, ChatAdapter.ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBubbleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChatViewHolder(
        private val binding: ItemChatBubbleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(msg: ChatMessage) {
            val isUser = msg.role == "user"
            val isAi = msg.role == "ai"
            val isSystem = msg.role == "system"
            val ctx = binding.root.context

            val lp = binding.bubbleContainer.layoutParams as FrameLayout.LayoutParams
            lp.gravity = if (isUser) Gravity.END else Gravity.START
            binding.bubbleContainer.layoutParams = lp

            val bgColor = when {
                isSystem -> ContextCompat.getColor(ctx, R.color.bubble_system)
                isAi -> ContextCompat.getColor(ctx, R.color.bubble_ai)
                else -> ContextCompat.getColor(ctx, R.color.bubble_user)
            }
            binding.bubbleCard.setCardBackgroundColor(bgColor)

            binding.tvContent.text = msg.content

            if (msg.screenshotBase64 != null) {
                binding.ivScreenshot.visibility = View.VISIBLE
                try {
                    val bytes = Base64.decode(msg.screenshotBase64, Base64.NO_WRAP)
                    val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    binding.ivScreenshot.setImageBitmap(bmp)
                } catch (_: Exception) {
                    binding.ivScreenshot.visibility = View.GONE
                }
            } else {
                binding.ivScreenshot.visibility = View.GONE
                binding.ivScreenshot.setImageDrawable(null)
            }

            val action = msg.action
            if (action != null) {
                binding.divider.visibility = View.VISIBLE
                binding.tvActionType.visibility = View.VISIBLE
                binding.tvActionType.text = "执行操作: ${action.type.uppercase()}"

                when (action.type) {
                    "click" -> {
                        binding.tvActionDetail.visibility = View.VISIBLE
                        binding.tvActionDetail.text = "坐标: (${action.x}, ${action.y})"
                        binding.tvActionDetail.setTextColor(
                            ContextCompat.getColor(ctx, R.color.gray)
                        )
                    }
                    else -> {
                        binding.tvActionDetail.visibility = View.GONE
                    }
                }

                if (action.type == "click") {
                    binding.btnExecute.visibility = View.VISIBLE
                    binding.btnExecute.setOnClickListener { onConfirmAction(action) }
                } else {
                    binding.btnExecute.visibility = View.GONE
                }
            } else {
                binding.divider.visibility = View.GONE
                binding.tvActionType.visibility = View.GONE
                binding.tvActionDetail.visibility = View.GONE
                binding.btnExecute.visibility = View.GONE
            }

            binding.tvTimestamp.text = SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(Date(msg.timestamp))
        }
    }

    class ChatDiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }
}