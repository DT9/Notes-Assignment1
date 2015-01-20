package com.ualberta.dtruong1_notes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ClaimListManager {
	static String prefFile = "ClaimList";
	Context context;
	private String slkey = "claimList";
	
	public ClaimListManager(Context context) {
		this.context = context;
	}
	
	public ClaimListController loadClaimInfo() throws StreamCorruptedException, IOException, ClassNotFoundException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String ListData = settings.getString("claimlist", "");
		if (ListData.equals("")) {
			return new ClaimListController();
		} else {
			return ClaimListFromString(ListData);
		}
	}
	
	
	
	private ClaimListController ClaimListFromString(String listData) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(listData.getBytes());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimListController) oi.readObject();
	}

	public void saveClaimInfo(ClaimListController sl) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slkey,claimInfoToString(sl));
	}

	private String claimInfoToString(ClaimListController sl) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(sl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return new String(bytes);
	}
}
