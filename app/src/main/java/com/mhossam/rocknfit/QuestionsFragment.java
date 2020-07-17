package com.mhossam.rocknfit;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mhossam.rocknfit.API.APIClient;
import com.mhossam.rocknfit.API.APIInterface;
import com.mhossam.rocknfit.Utils.LinearLayoutManagerWrapper;
import com.mhossam.rocknfit.model.Question;
import com.mhossam.rocknfit.ui.activity.NewsFeedActivity;
import com.mhossam.rocknfit.view.FeedContextMenuManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class QuestionsFragment extends Fragment {

    private APIInterface apiInterface;
    private int currentPage = 0;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    ArrayList<Question> questionsList;
    private QuestionAdapter questionAdapter;
    private boolean loading;

    @BindView(R.id.list)
    RecyclerView recyclerView;


    @BindView(R.id.btn_ham_menu)
    ImageButton btnHamMenu;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuestionsFragment newInstance(int columnCount) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        getQuestions();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    private void getQuestions() {
        loading = true;
        Map<String, String> requestMap = prepareRequestMap();
        requestMap.put("QuestionID", "0");
        requestMap.put("Index", currentPage + "");
        requestMap.put("Size", "10");

        Call<Map<String, Question>> call = apiInterface.getQuestions(requestMap);
        call.enqueue(new Callback<Map<String, Question>>() {
            @Override
            public void onResponse(Call<Map<String, Question>> call, Response<Map<String, Question>> response) {
                Log.d("TAG", response.code() + "");
                Map<String, Question> resource = response.body();
                if (resource != null) {
                    questionsList = new ArrayList<>(resource.values());
                    questionAdapter.updateItems(questionsList);
                    currentPage++;
                }
                loading = false;
            }

            @Override
            public void onFailure(Call<Map<String, Question>> call, Throwable t) {
                call.cancel();
                loading = false;
            }
        });
    }

    private Map<String, String> prepareRequestMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Action", "GetAllQuestions");
        requestMap.put("ApiUser", "Test");
        requestMap.put("ApiPass", "Test");
        return requestMap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions_list, container, false);
        ButterKnife.bind(this , view);
        questionAdapter = new QuestionAdapter(new ArrayList<Question>());
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
//            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(context));
            recyclerView.setAdapter(questionAdapter);
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        getQuestions();
                    }
                }
            });
        }

        btnHamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = getActivity().findViewById(R.id.drawerLayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });
        return view;
    }

}