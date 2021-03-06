package comnathanromike.github.geomatch.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.models.PuzzlePhoto;
import comnathanromike.github.geomatch.ui.LocationActivity;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.makeGuessButton) Button mMakeGuessButton;
    @Bind(R.id.photoImageView) ImageView mImageMedium;
    @Bind(R.id.dateTextView) TextView mDateTextView;
    PhotoViewAttacher mAttacher;
    private PuzzlePhoto mPuzzlePhoto;

    public PhotoDetailFragment() {}

    public static PhotoDetailFragment newInstance(PuzzlePhoto puzzlePhoto) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", Parcels.wrap(puzzlePhoto));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPuzzlePhoto = Parcels.unwrap(getArguments().getParcelable("photo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);
        mMakeGuessButton.setOnClickListener(this);

        mDateTextView.setText(mPuzzlePhoto.getLatitude().toString());

        mAttacher = new PhotoViewAttacher(mImageMedium);

        Picasso.with(view.getContext())
                .load(mPuzzlePhoto.getMediumPhotoUrl())
                .into(mImageMedium);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        intent.putExtra("photoId", mPuzzlePhoto.getPhotoId());
        startActivity(intent);
    }
}
