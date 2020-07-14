package com.mhossam.rocknfit.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.mhossam.rocknfit.R;
import com.mhossam.rocknfit.Utils.CircleTransformation;
import com.mhossam.rocknfit.database.AppDatabase;
import com.mhossam.rocknfit.model.LoggedInUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    @BindView(R.id.photo)
    CircleImageView profilePhoto;
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.name)
    TextView name;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "rockAndFit").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        LoggedInUser currentUser  = db.loggedInUserDao().getLoggedInUser();


        if(currentUser != null){
            String origUserProfilePhoto = "https://www.fitandrock.com/ProfilePictures/Org"+currentUser.getAccountImage();
            String coverPhoto = "https://www.fitandrock.com/Uploads/"+currentUser.getAccountContainer()+"/CoverPhotos/"+currentUser.getAccountCover();
            Picasso.get()
                    .load(origUserProfilePhoto)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .fit()
                    .transform(new CircleTransformation())
                    .into(profilePhoto);

            Picasso.get()
                    .load(coverPhoto)
//                    .placeholder(R.drawable.img_circle_placeholder)
                    .fit()
//                    .transform(new CircleTransformation())
                    .into(banner);
        }
        name.setText(currentUser.getAccountFirstName()+" "+currentUser.getAccountLastName());
        return root;
    }
}