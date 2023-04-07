/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageHolder> {

    private static final String TAG = ChatAdapter.class.getSimpleName();
    private final Context mContext;
    private int switchflag = 0;
    private List<Message> messages;

    public ChatAdapter(Context context, List<Message> items) {
        mContext = context;
        messages = items;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);

        return new MessageHolder(messageView);
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message message = messages.get(position);

        if (message != null) {
            if (message.getText() != null) {
                holder.messagetext.setText(message.getText());
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.messagetext.getLayoutParams();
                if (switchflag == 1) {
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    holder.relativeLayout.setLayoutParams(params);
                    holder.messagetext.setText(message.getText());
                    switchflag = 0;
                } else {
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    holder.relativeLayout.setLayoutParams(params);
                    holder.messagetext.setText(message.getText());
                    switchflag = 1;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setItems(List<Message> messages) {
        if (messages != null && !messages.isEmpty()) {
            this.messages = messages;
            notifyDataSetChanged();
        }

    }

    public class MessageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.messagetext)
        TextView messagetext;

        @BindView(R.id.containerchatmessage)
        RelativeLayout relativeLayout;

        public MessageHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

