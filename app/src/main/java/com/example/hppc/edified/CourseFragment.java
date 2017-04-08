package com.example.hppc.edified;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment implements FireBaseConn {

    private static final String TAG = "CourseFragment";
    private static final String COURSES_CHILD = "courses";
    FirebaseUser user = mAuth.getCurrentUser();
    private RecyclerView courseRecyclerView;
    private RecyclerView.LayoutManager courseLayoutManager;
    private FirebaseRecyclerAdapter<Course, CourseHolder> courseAdapter;
    private FragmentTransaction fragmentTransaction;
    private Course crs;
    private FloatingActionButton fab;
    private DatabaseReference mUserReference;
    private ValueEventListener mUserListener;
    private User usr;
    private String role;

//    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CourseFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CourseFragment newInstance(String param1, String param2) {
//        CourseFragment fragment = new CourseFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        courseRecyclerView = (RecyclerView) view.findViewById(R.id.courseRecView);
        courseLayoutManager = new LinearLayoutManager(getActivity());
        courseRecyclerView.setLayoutManager(courseLayoutManager);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        fab.hide();

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.USERS)).child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                role = user.getRole();
                Log.v(TAG, role);
                if (role.equals(R.string.faculty)) {
                    fab.show();
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), AddCourse.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        courseAdapter = new FirebaseRecyclerAdapter<Course, CourseHolder>(
                Course.class,
                R.layout.course_card,
                CourseHolder.class,
                mDatabase.child(COURSES_CHILD)) {

            @Override
            protected Course parseSnapshot(DataSnapshot snapshot) {
                Course course = super.parseSnapshot(snapshot);
                if (course != null) {
                    course.setCourseID(snapshot.getKey());
                }
                return course;
            }

            @Override
            protected void populateViewHolder(CourseHolder viewHolder, Course model, int position) {
                crs = model;
                viewHolder.getCourse_name().setText(model.getCourseName());
                viewHolder.getCourse_category().setText(model.getCourseCategory());
                viewHolder.getEnroll().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase.getInstance().getReference()
                                .child(getString(R.string.USERS)).child(user.getUid()).child("enrolledCourses").push().setValue(crs);
                    }
                });
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), CourseDescription.class);
                        intent.putExtra("Course", (Parcelable) crs);
                        startActivity(intent);
                    }
                });
            }
        };

        courseRecyclerView.setAdapter(courseAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        ValueEventListener userListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                usr = dataSnapshot.getValue(User.class);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//
//                Toast.makeText(getContext(), "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        };
//        mUserReference.addValueEventListener(userListener);
//        mUserListener = userListener;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
