package com.example.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_TIME =
			"com.bignerdranch.android.criminalintent.time";

	private Date mTime;
	private Date mNewTime;

	public static TimePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TIME, date);
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {		 
		mTime = (Date)getArguments().getSerializable(EXTRA_TIME);

		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_time, null);

		// Create a Calendar to get the year, month, and day


		TimePicker timePicker = (TimePicker)v.findViewById(R.id.dialog_time_timePicker);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener(){
			public void onTimeChanged(TimePicker view, int hourOfDay, int minuteOfHour) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mTime);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);

				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				int second = calendar.get(Calendar.SECOND);

				mNewTime = new GregorianCalendar(year, month, day, hourOfDay, minuteOfHour, second).getTime();
				// Update argument to preserve selected value on rotation
				Log.d("hello", mNewTime.toString());
				getArguments().putSerializable(EXTRA_TIME, mNewTime);
			}
		});

		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.time_picker_title)
		.setPositiveButton(
				android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
				.create();
	}

	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_TIME, mNewTime);
		getTargetFragment()
		.onActivityResult(getTargetRequestCode(), resultCode, i);
	}



}
