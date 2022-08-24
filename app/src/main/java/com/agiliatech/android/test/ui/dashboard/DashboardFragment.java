package com.agiliatech.android.test.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agiliatech.android.test.ModelResponse.FetchSessionResponse;
import com.agiliatech.android.test.ModelResponse.Session;
import com.agiliatech.android.test.R;

import com.agiliatech.android.test.Utils;
import com.agiliatech.android.test.ui.dashboard.Api.RetrofitClient;
import com.agiliatech.android.test.databinding.FragmentDashboardBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.faltenreich.skeletonlayout.SkeletonLayout;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.agiliatech.android.test.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout skeletonLayout;
    private TextView endFirstFreeSession;
    private LinearLayout firstLayout;
    public List<Session> sessionList;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycle);
        endFirstFreeSession = view.findViewById(R.id.end_free_session_first);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Retrofit Call

        Call<FetchSessionResponse> call = RetrofitClient.getInstance().getApi().fetchAllSessions();

        try {

            call.enqueue(new Callback<FetchSessionResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<FetchSessionResponse> call, Response<FetchSessionResponse> response) {

                    if (response.isSuccessful()) {

                        //Layout Animation Stop
                        skeletonLayout = view.findViewById(R.id.skeleton);
                        firstLayout = view.findViewById(R.id.first_layout);
                        skeletonLayout.stopShimmer();
                        skeletonLayout.setVisibility(View.GONE);
                        firstLayout.setVisibility(View.VISIBLE);

                        //Get Api Response
                        sessionList = response.body().getSessionList();
                        //Sort the list
                        Collections.sort(sessionList);
                        //Setting endTime for first free session
                        String string = Utils.getTimeFromMillisec((sessionList.get(0).getStart_time_ms() - 300000));
                        endFirstFreeSession.setText(string);

                        //Getting final list including only first day Sessions
                        List<Session> finalList = Utils.getFirstDaySessions(sessionList);

                        //Setting the finalList to the adapter
                        recyclerView.setAdapter(new Adapter(getActivity(), finalList));


                    } else {
                        Toast.makeText(getActivity(), "An Error Occured", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<FetchSessionResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "An Error Occured: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}