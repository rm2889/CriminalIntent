package com.example.criminalintent;

import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_TIME =
			"com.bignerdranch.android.criminalintent.time";

	private Date mDate;

	public static TimePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TIME, date);
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View v = getActivity().getLayoutInflater()
		        .inflate(R.layout.dialog_time, null);
		
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.time_picker_title)
		.setPositiveButton(android.R.string.ok, null)
		.create();
	}



}
