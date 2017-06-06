package rp.edu.sg.c302.addressbook;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class DisplayUserInfoActivity extends Activity {

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_display_user_info);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	protected void onStart(){
		super.onStart();
		Intent intent = getIntent();
		userId = intent.getStringExtra("com.example.MAIN_MESSAGE");
		HttpRequest request= new HttpRequest("http://10.0.2.2/C302_CloudAddressBook/getContactDetails.php?Id=" + userId);
		request.setMethod("GET");
		request.execute();
		try{
			String jsonString = request.getResponse();
			JSONObject jsonObj = new JSONObject(jsonString);
			// TODO 01: Set values in the EditText fields
			EditText etFirstName = (EditText)findViewById(R.id.editTextFirstName);
			etFirstName.setText(jsonObj.getString("firstname"));

			EditText etLastName = (EditText)findViewById(R.id.editTextLastName);
			etLastName.setText(jsonObj.getString("lastname"));

			EditText etHome = (EditText)findViewById(R.id.editTextHome);
			etHome.setText(jsonObj.getString("home"));

			EditText etMobile = (EditText)findViewById(R.id.editTextMobile);
			etMobile.setText(jsonObj.getString("mobile"));

			EditText etAddress = (EditText)findViewById(R.id.editTextAddress);
			etAddress.setText(jsonObj.getString("address"));

			EditText etCountry = (EditText)findViewById(R.id.editTextCountry);
			etCountry.setText(jsonObj.getString("country"));

			EditText etPostalCode = (EditText)findViewById(R.id.editTextPostalCode);
			etPostalCode.setText(jsonObj.getString("postalcode"));

			EditText etEmail = (EditText)findViewById(R.id.editTextEmail);
			etEmail.setText(jsonObj.getString("email"));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDetailsButtonClicked(View view){
		
		EditText firstNameEditText = (EditText)findViewById(R.id.editTextFirstName);		
		EditText lastNameEditText = (EditText)findViewById(R.id.editTextLastName);		
		EditText homeEditText = (EditText)findViewById(R.id.editTextHome);		
		EditText mobileEditText = (EditText)findViewById(R.id.editTextMobile);		
		EditText addressEditText = (EditText)findViewById(R.id.editTextAddress);		
		EditText countryEditText = (EditText)findViewById(R.id.editTextCountry);		
		EditText postalCodeEditText = (EditText)findViewById(R.id.editTextPostalCode);		
		EditText emailEditText = (EditText)findViewById(R.id.editTextEmail);		
		
		//TODO 03: Send the HttpRequest to updateContact.php
		Toast.makeText(DisplayUserInfoActivity.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();
		HttpRequest request = new HttpRequest("http://10.0.2.2/C302_CloudAddressBook/updateContact.php");
		request.setMethod("POST");
		request.addData("id",userId);
		request.addData("firstname",firstNameEditText.getText().toString());
		request.addData("lastname",lastNameEditText.getText().toString());
		request.addData("home",homeEditText.getText().toString());
		request.addData("mobile",mobileEditText.getText().toString());
		request.addData("address",addressEditText.getText().toString());
		request.addData("country",countryEditText.getText().toString());
		request.addData("postalcode",postalCodeEditText.getText().toString());
		request.addData("email",emailEditText.getText().toString());
		request.execute();
		try{

			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteRecordButtonClicked(View view){
		//TODO 04: Send HttpRequest to removeContact.php
		Toast.makeText(DisplayUserInfoActivity.this, "Perform TODO task 4", Toast.LENGTH_SHORT).show();
		HttpRequest request = new HttpRequest("http://10.0.2.2/C302_CloudAddressBook/removeContact.php");
		request.setMethod("POST");
		request.addData("Id",userId);
		request.execute();

		try{

			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_user_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_display_user_info, container, false);
			return rootView;
		}
	}

}