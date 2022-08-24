package com.agiliatech.android.test.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agiliatech.android.test.ModelResponse.FetchSessionResponse;
import com.agiliatech.android.test.ModelResponse.Session;
import com.agiliatech.android.test.R;
import com.agiliatech.android.test.Utils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Session> sessionList;
    Context context;

    public Adapter(Context context, List<Session> sessionList ) {
        this.context = context;
        this.sessionList = sessionList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.appt_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

            //Set Client Name
            holder.clientName.setText(sessionList.get(position).getClient_name());

            //Set Client Gender
            if (sessionList.get(position).getClient_gender() == 1) {
                holder.clientGender.setText("Gender: Male");
            } else if (sessionList.get(position).getClient_gender() == 2) {
                holder.clientGender.setText("Gender: Female");
            }

            //Set Status
            if (sessionList.get(position).getSession_number() == 1 && sessionList.get(position).getSessions_count() == 1) {
                holder.clientStatus.setText("Single");
            } else {
                holder.clientStatus.setText("#" + sessionList.get(position).getSession_number() + "/"
                        + sessionList.get(position).getSessions_count());
            }

            //Set Service Name
            holder.serviceName.setText(sessionList.get(position).getService_name());

            //Set Client Image
            Picasso.get().load(sessionList.get(position).getClient_pic()).into(holder.clientImage);
            holder.sessionPlace.setText(sessionList.get(position).getSession_place());

            //Set Session start time and end time
            long startTime = sessionList.get(position).getStart_time_ms();
            long endTime = sessionList.get(position).getEnd_time_ms();
            String s1 = Utils.getTimeFromMillisec(startTime);
            String s2 = Utils.getTimeFromMillisec(endTime);
            holder.startTime.setText(s1);
            holder.endTime.setText(s2);

            //Set Free Session start and end time
            if (position == sessionList.size() - 1) {
                long lastFreeSessionStart = sessionList.get(position).getEnd_time_ms() + 300000;
                String s5 = Utils.getTimeFromMillisec(lastFreeSessionStart);
                holder.startFreeSession.setText(s5);
                holder.endFreeSession.setText("23:55");

            } else {
                long freeSessionStartTime = sessionList.get(position).getEnd_time_ms() + 300000;
                long freeSessionEndTime = sessionList.get(position + 1).getStart_time_ms() - 300000;
                String s3 = Utils.getTimeFromMillisec(freeSessionStartTime);
                String s4 = Utils.getTimeFromMillisec(freeSessionEndTime);
                holder.startFreeSession.setText(s3);
                holder.endFreeSession.setText(s4);
            }




    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView clientName, clientGender, clientStatus, sessionPlace, serviceName,
                startTime, endTime, startFreeSession, endFreeSession;
        ImageView clientImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            clientGender = itemView.findViewById(R.id.client_gender);
            clientImage = itemView.findViewById(R.id.client_pic);
            clientStatus = itemView.findViewById(R.id.client_status);
            sessionPlace = itemView.findViewById(R.id.session_place);
            serviceName = itemView.findViewById(R.id.service_name);
            startTime = itemView.findViewById(R.id.start_time);
            endTime = itemView.findViewById(R.id.end_time);
            startFreeSession = itemView.findViewById(R.id.start_free_session);
            endFreeSession = itemView.findViewById(R.id.end_free_session);

        }
    }
}
