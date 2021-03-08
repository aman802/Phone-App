package com.aman802.phoneapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class LauncherActivity extends AppCompatActivity implements ListScrollInterface {
    private BottomNavigationView bottomNavigationView;
    private ImageView dialerMoreOptions;
    private ConstraintLayout dialerConstraintLayout, topBarConstraintLayout;
    private FloatingActionButton dialerFab;
    private EditText inputEditText;
    private Context context;
    private static final int PHONE_PERMISSION = 1;
    private static final int DEFAULT_PHONE_PERMISSION = 2;
    private final String[] permissions = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG
    };

    private final ArrayList<String> unGrantedPermissions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        context = this;

        Util.applyThemePreference(context);

        if (!checkIfPermissionsGranted()) requestForPermissions();

        else init();
    }

    private void init() {
        topBarConstraintLayout = findViewById(R.id.top_bar_constraint_layout);
        dialerConstraintLayout = findViewById(R.id.dialer_constraint_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        ImageView themeIcon = findViewById(R.id.theme_icon);
        TextView tv0 = findViewById(R.id.key_0);
        TextView tv1 = findViewById(R.id.key_1);
        TextView tv2 = findViewById(R.id.key_2);
        TextView tv3 = findViewById(R.id.key_3);
        TextView tv4 = findViewById(R.id.key_4);
        TextView tv5 = findViewById(R.id.key_5);
        TextView tv6 = findViewById(R.id.key_6);
        TextView tv7 = findViewById(R.id.key_7);
        TextView tv8 = findViewById(R.id.key_8);
        TextView tv9 = findViewById(R.id.key_9);
        TextView tvStar = findViewById(R.id.key_star);
        TextView tvHash = findViewById(R.id.key_hash);
        inputEditText = findViewById(R.id.input_edit_text);

        ImageView backSpace = findViewById(R.id.recents_clear_button);
        final ImageView callButton = findViewById(R.id.call_button);
        dialerMoreOptions = findViewById(R.id.recents_more_options);

        dialerFab = findViewById(R.id.dialer_fab);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return false;
            }
        });

        topBarConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreference.setSearchActive(LauncherActivity.this, true);
                startActivity(new Intent(LauncherActivity.this, SearchActivity.class));
            }
        });

        themeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showThemeDialog();
            }
        });

        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("0");
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("1");
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("2");
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("3");
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("4");
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("5");
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("6");
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("7");
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("8");
            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("9");
            }
        });
        tvStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("*");
            }
        });
        tvHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberClick("#");
            }
        });
        tv0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onNumberClick("+");
                return true;
            }
        });
        backSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentString = inputEditText.getText().toString();
                if (currentString.length() > 0) {
                    currentString = currentString.substring(0, currentString.length() - 1);
                    inputEditText.setText(currentString);
                }
            }
        });
        backSpace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                inputEditText.setText("");
                return true;
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentString = inputEditText.getText().toString();
                if (!currentString.isEmpty()) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(LauncherActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_PERMISSION);
                    }
                    else Util.makeCall(context, currentString);
                }
            }
        });
        dialerMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String currentString = inputEditText.getText().toString();
                PopupMenu menu = new PopupMenu(context, dialerMoreOptions);
                menu.getMenuInflater().inflate(R.menu.dialer_popup_menu, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_add_new_contact:
                                Util.addNewContact(context, currentString);
                                break;

                            case R.id.menu_add_existing_contact:
                                Util.addExistingContact(context, currentString);
                                break;
                        }
                        return true;
                    }
                });

                menu.show();
            }
        });
        dialerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialerConstraintLayout.setVisibility(View.VISIBLE);
                dialerConstraintLayout.animate().translationY(0);

                topBarConstraintLayout.setVisibility(View.GONE);
                topBarConstraintLayout.animate().translationY(topBarConstraintLayout.getHeight() * -1);

                bottomNavigationView.setVisibility(View.GONE);
                bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());

                dialerFab.hide();
            }
        });

        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    dialerMoreOptions.setVisibility(View.VISIBLE);
                }
                else {
                    dialerMoreOptions.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        openCurrentlySelectedTab();
        checkForIntentData();
        checkIfDefaultApp();
    }

    private void checkIfDefaultApp() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return;

        TelecomManager telecomManager = (TelecomManager) getSystemService(TELECOM_SERVICE);
        String packageName = getPackageName();
        if (packageName.equals(telecomManager.getDefaultDialerPackage())) return;

        long lastCheckMillis = SharedPreference.getLastDefaultAppCheck(this);
        if (lastCheckMillis != -1) {
            long difference = Math.abs(System.currentTimeMillis() - lastCheckMillis);
            long differenceDays = difference / (24 * 60 * 60 * 1000);

            // Check if it has been a week since last asked for default app
            if (differenceDays > 7) {
//                Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
//                        .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName);
//
//                startActivityForResult(intent, DEFAULT_PHONE_PERMISSION);

                SharedPreference.setLastDefaultAppCheck(this);
                RoleManager roleManager = (RoleManager) getSystemService(ROLE_SERVICE);
                Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER);
                startActivityForResult(intent, DEFAULT_PHONE_PERMISSION);
            }
        }
        else {
//            Log.d("Else", packageName);
//            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
//                    .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName);
//
//            startActivityForResult(intent, DEFAULT_PHONE_PERMISSION);

            SharedPreference.setLastDefaultAppCheck(this);
            RoleManager roleManager = (RoleManager) getSystemService(ROLE_SERVICE);
            Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER);
            startActivityForResult(intent, DEFAULT_PHONE_PERMISSION);
        }
    }

    private void checkForIntentData() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            String intentNumber = intent.getData().getSchemeSpecificPart();
            if (intentNumber != null && !intentNumber.isEmpty()) {
                onNumberClick(intentNumber);
                dialerFab.performClick();
            }
        }
    }

    private void openCurrentlySelectedTab() {
        int selectedBottomTab = SharedPreference.getSelectedBottomTab(this);
        bottomNavigationView.getMenu().getItem(selectedBottomTab).setChecked(true);
        switch (selectedBottomTab) {
            case 0: pushFragment(new FavoritesFragment()); break;
            case 1: pushFragment(new RecentsFragment()); break;
            case 2: pushFragment(new ContactsFragment()); break;
        }
    }

    private void selectFragment(MenuItem item) {
        item.setChecked(true);
        int itemId = item.getItemId();
        Fragment fragment = null;
        if (itemId == R.id.menu_favorites && SharedPreference.getSelectedBottomTab(this) != 0) {
            fragment = new FavoritesFragment();
            SharedPreference.setSelectedBottomTab(this, 0);
        }
        else if (itemId == R.id.menu_recents && SharedPreference.getSelectedBottomTab(this) != 1) {
            fragment = new RecentsFragment();
            SharedPreference.setSelectedBottomTab(this, 1);
        }
        else if (itemId == R.id.menu_contacts && SharedPreference.getSelectedBottomTab(this) != 2) {
            fragment = new ContactsFragment();
            SharedPreference.setSelectedBottomTab(this, 2);
        }
        if (fragment != null) pushFragment(fragment);
    }

    private void pushFragment(Fragment fragment) {
        if (fragment == null) return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    private boolean checkIfPermissionsGranted() {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                unGrantedPermissions.add(permission);
            }
        }
        return unGrantedPermissions.isEmpty();
    }

    private void requestForPermissions() {
        ActivityCompat.requestPermissions(this, unGrantedPermissions.toArray(new String[0]), PHONE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PHONE_PERMISSION) {
            unGrantedPermissions.clear();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    unGrantedPermissions.add(permissions[i]);
                }
            }

            if (!unGrantedPermissions.isEmpty()) {
                Toast.makeText(this, "App needs these permissions to work", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                init();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DEFAULT_PHONE_PERMISSION) {
            String message;
            switch (resultCode) {
                case RESULT_OK:
                    message = "Thank you for setting as Default app";
                    break;
                case RESULT_CANCELED:
                    message = "App not set as default";
                    break;
                default:
                    message = "Unexpected error code " + resultCode;
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void showThemeDialog() {
        int currentTheme = SharedPreference.getThemePreference(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppThemeAlertDialog);
        final String[] themes = getResources().getStringArray(R.array.themes);
        builder
                .setTitle("Choose Theme")
                .setSingleChoiceItems(themes, currentTheme, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                        SharedPreference.setThemePreference(context, selectedPosition);
                        Util.applyThemePreference(context);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void onNumberClick(String number) {
        String currentString = inputEditText.getText().toString();
        currentString += number;
        inputEditText.setText(currentString);
    }

    @Override
    public void onListScroll() {
        dialerConstraintLayout.setVisibility(View.GONE);
        dialerConstraintLayout.animate().translationY(dialerConstraintLayout.getHeight());

        topBarConstraintLayout.setVisibility(View.VISIBLE);
        topBarConstraintLayout.animate().translationY(0);

        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.animate().translationY(0);

        dialerFab.show();
    }

    @Override
    public void onBackPressed() {
        if (!dialerFab.isShown()) onListScroll();
        else super.onBackPressed();
    }
}