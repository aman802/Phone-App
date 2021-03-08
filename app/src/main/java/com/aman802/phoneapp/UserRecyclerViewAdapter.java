package com.aman802.phoneapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.qtalk.recyclerviewfastscroller.RecyclerViewFastScroller;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> implements Filterable, RecyclerViewFastScroller.OnPopupTextUpdate {
    private final Context context;
    private final ArrayList<User> userList;
    private ArrayList<User> userListFiltered;
    private final LayoutInflater inflater;
    private final int topMargin = Util.dpToPixel(15);

    public UserRecyclerViewAdapter(Context context, ArrayList<User> userList, LayoutInflater inflater) {
        this.context = context;
        this.userList = userList;
        this.userListFiltered = userList;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = inflater.inflate(R.layout.item_favorite_contact, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User currentUser = userListFiltered.get(position);

        String displayName = currentUser.getDisplayName();
        holder.nameTextView.setText(displayName);

        String number = currentUser.getNumber();
        boolean isNumberAvailable = number != null && !number.isEmpty();
        if (isNumberAvailable) holder.numberTextView.setText(number);

        switch (currentUser.getType()) {
            case CallLog.Calls.OUTGOING_TYPE:
                holder.callTypeImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_outgoing));
                break;

            case CallLog.Calls.INCOMING_TYPE:
                holder.callTypeImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_incoming));
                break;

            case CallLog.Calls.MISSED_TYPE:
                holder.callTypeImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_missed));
                break;

            default:
                if (!isNumberAvailable) {
                    holder.otherInfoConstraintLayout.setVisibility(View.GONE);
                    holder.callIcon.setVisibility(View.GONE);
                }
                else holder.callTypeImageView.setVisibility(View.GONE);
        }

        if (currentUser.getCallDate() != -1) {
            String dateString = Util.getDateString(currentUser.getCallDate());
            holder.callDateTextView.setVisibility(View.VISIBLE);
            holder.callDateTextView.setText(dateString);
        }
        else {
            holder.callDateTextView.setVisibility(View.GONE);
        }

        int color = Util.getColorFromName(context, currentUser.getName());
        if (!displayName.equals("Unknown")) {
            holder.userTextImage.setText(String.valueOf(displayName.charAt(0)));
            Drawable background = holder.userTextImage.getBackground();
            background.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            holder.userTextImage.setVisibility(View.VISIBLE);
            holder.userImageView.setVisibility(View.GONE);
        }
        else {
            holder.userImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_person));
            Drawable background = holder.userImageView.getBackground();
            background.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            holder.userImageView.setVisibility(View.VISIBLE);
            holder.userTextImage.setVisibility(View.GONE);
        }
        holder.callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.makeCall(context, currentUser.getNumber());
            }
        });

        holder.superConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Only trigger onClick for "Contacts" tab
                if (SharedPreference.getSelectedBottomTab(context) == 2 || SharedPreference.isSearchActive(context)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, currentUser.getId());
                    intent.setData(uri);
                    context.startActivity(intent);
                }
            }
        });

        if (position + 1 == userList.size()) {
            setBottomMargin(holder.itemView, Util.dpToPixel(72));
        }
        else {
            setBottomMargin(holder.itemView, -1);
        }
    }

    @Override
    public int getItemCount() {
        if (userList != null) return userListFiltered.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    userListFiltered = userList;
                }
                else {
                    ArrayList<User> filteredUsers = new ArrayList<>();
                    for (User user : userList) {
                        if (user.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredUsers.add(user);
                        }
                    }
                    userListFiltered = filteredUsers;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userListFiltered = (ArrayList<User>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void updateList(ArrayList<User> newList) {
        userList.clear();
        userList.addAll(newList);
        this.notifyDataSetChanged();
    }

    private void setBottomMargin(View view, int bottomMargin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            if (bottomMargin != -1) {
                params.setMargins(params.leftMargin, topMargin, params.rightMargin, bottomMargin);
            }
            else {
                params.setMargins(params.leftMargin, topMargin, params.rightMargin, topMargin);
            }
            view.requestLayout();
        }
    }

    @NotNull
    @Override
    public CharSequence onChange(int i) {
        User user = userList.get(i);
        return String.valueOf(user.getDisplayName().charAt(0));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout superConstraintLayout;
        private final ConstraintLayout otherInfoConstraintLayout;
        private final TextView nameTextView;
        private final TextView numberTextView;
        private final TextView callDateTextView;
        private final TextView userTextImage;
        private final ImageView callIcon;
        private final ImageView userImageView;
        private final ImageView callTypeImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            superConstraintLayout = itemView.findViewById(R.id.item_super_constraint_layout);
            otherInfoConstraintLayout = itemView.findViewById(R.id.other_info_constraint_layout);
            nameTextView = itemView.findViewById(R.id.user_name_text_view);
            numberTextView = itemView.findViewById(R.id.user_number_text_view);
            callIcon = itemView.findViewById(R.id.list_call_icon);
            userTextImage = itemView.findViewById(R.id.user_text_image);
            userImageView = itemView.findViewById(R.id.user_image_view);
            callTypeImageView = itemView.findViewById(R.id.call_type_image_view);
            callDateTextView = itemView.findViewById(R.id.new_call_time_text_view);
        }
    }

}
