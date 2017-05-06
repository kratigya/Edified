package com.example.hppc.edified;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
    private Course crs;
    private FloatingActionButton fab;
    private User usr;
    private String role;
    private ArrayList<Course> courseList = new ArrayList<>();
    private ProgressDialog progress;

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

        progress = new ProgressDialog(view.getContext());
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.USERS)).child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usr = dataSnapshot.getValue(User.class);
                role = usr.getRole();
                Log.v(TAG, role);
                if (role.equalsIgnoreCase(getString(R.string.faculty))) {
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
                    courseList.add(course);
                }
                return course;
            }

            @Override
            protected void populateViewHolder(CourseHolder viewHolder, Course model, int position) {
                progress.dismiss();
                viewHolder.getCourse_name().setText(model.getCourseName());
                viewHolder.getCourse_category().setText(model.getCourseCategory());
                if (usr.getEnrolledCourses().containsKey(model.getCourseID())) {
                    viewHolder.getEnroll().setVisibility(View.GONE);
                }
            }

            @Override
            public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final CourseHolder courseHolder = super.onCreateViewHolder(parent, viewType);
                courseHolder.setOnClickListener(new CourseHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        crs = courseList.get(position);
                        Intent intent = new Intent(getContext(), CourseDescription.class);
                        intent.putExtra("Course", (Parcelable) crs);
                        intent.putExtra("User", (Parcelable) usr);
                        startActivity(intent);
                    }

                    @Override
                    public void onEnrollClick(View view, int position) {
                        crs = courseList.get(position);
                        Log.v(TAG, crs.getCourseID());
                        FirebaseDatabase.getInstance().getReference()
                                .child(getString(R.string.USERS)).child(user.getUid())
                                .child("enrolledCourses").child(crs.getCourseID()).setValue(crs);
//                        courseHolder.getEnroll().setVisibility(View.GONE);
                    }
                });
                return courseHolder;
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
