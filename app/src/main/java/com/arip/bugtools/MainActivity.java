
package com.arip.bugtools;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    LinearLayout root;
    int bg = Color.rgb(12, 15, 28);
    int panel = Color.rgb(25, 30, 49);
    int accent = Color.rgb(92, 102, 255);
    int white = Color.WHITE;

    int dp(float n) {
        return (int) (n * getResources().getDisplayMetrics().density);
    }

    TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(white);
        t.setGravity(Gravity.CENTER_VERTICAL);
        return t;
    }

    void init(String title) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(24), dp(28), dp(24), dp(24));
        root.setBackgroundColor(bg);
        setContentView(root);

        TextView heading = text(title, 25);
        heading.setTypeface(null, Typeface.BOLD);
        root.addView(heading);
    }

    void space(int height) {
        View v = new View(this);
        root.addView(v, new LinearLayout.LayoutParams(1, dp(height)));
    }

    EditText input(String hint, boolean password) {
        EditText e = new EditText(this);
        e.setSingleLine(true);
        e.setTextColor(white);
        e.setHintTextColor(Color.LTGRAY);
        e.setHint(hint);
        e.setPadding(dp(14), dp(8), dp(14), dp(8));
        e.setBackgroundColor(panel);

        if (password) {
            e.setInputType(129);
        }

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, dp(54));
        p.bottomMargin = dp(14);
        root.addView(e, p);
        return e;
    }

    void button(String label, Runnable action) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(white);
        b.setAllCaps(false);
        b.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(accent)
        );
        root.addView(b, new LinearLayout.LayoutParams(-1, dp(52)));
        b.setOnClickListener(v -> action.run());
    }

    void note(String value) {
        TextView t = text(value, 14);
        t.setTextColor(Color.LTGRAY);
        t.setPadding(0, dp(8), 0, dp(8));
        root.addView(t);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        login();
    }

    void login() {
        init("ARIP BUG TOOLS");
        space(8);
        note("DEVELOPER CONSOLE  •  ANDROID");
        space(36);

        TextView welcome = text("Welcome back.", 23);
        welcome.setTypeface(null, Typeface.BOLD);
        root.addView(welcome);
        note("Login untuk membuka dashboard pengujian.");

        space(20);
        EditText username = input("Username", false);
        EditText password = input("Password", true);

        button("LOGIN DEVELOPER", () -> {
            String u = username.getText().toString().trim();
            String p = password.getText().toString();

            if (u.equals("Arip") && p.equals("Arip")) {
                dashboard();
            } else {
                Toast.makeText(
                    this, "Username atau password salah",
                    Toast.LENGTH_SHORT
                ).show();
            }
        });

        space(20);
        note("Device tools • Network checks • Bug reports");
        note("Demo login lokal. Jangan gunakan password ini untuk akun penting.");
    }

    void dashboard() {
        init("ARIP  /  DEVELOPER");
        space(8);
        note("DEVELOPER ACCESS");
        space(20);

        TextView videoPanel = text(
            "▶   VIDEO / MEDIA PANEL\n\n" +
            "Media panel siap dikembangkan.",
            17
        );
        videoPanel.setGravity(Gravity.CENTER);
        videoPanel.setBackgroundColor(panel);
        root.addView(videoPanel,
            new LinearLayout.LayoutParams(-1, dp(150)));

        space(24);
        note("TOOLS DASHBOARD");

        button("NETWORK TEST INFO", () -> {
            new android.app.AlertDialog.Builder(this)
                .setTitle("Network Tester")
                .setMessage(
                    "Gunakan pengaturan Android untuk memeriksa " +
                    "koneksi. Pengujian aktif dapat ditambahkan " +
                    "pada versi berikutnya."
                )
                .setPositiveButton("OK", null)
                .show();
        });

        space(10);
        button("DEVICE TEST INFO", () -> {
            new android.app.AlertDialog.Builder(this)
                .setTitle("Device Tester")
                .setMessage(
                    "Dashboard demo berhasil dibuka. " +
                    "Pengujian performa perangkat belum diaktifkan."
                )
                .setPositiveButton("OK", null)
                .show();
        });

        space(10);
        button("BUG REPORT", () -> {
            EditText report = new EditText(this);
            report.setHint("Tulis laporan bug...");
            new android.app.AlertDialog.Builder(this)
                .setTitle("Bug Report")
                .setView(report)
                .setPositiveButton("Selesai", (d, w) ->
                    Toast.makeText(
                        this, "Laporan ditutup. Belum disimpan.",
                        Toast.LENGTH_SHORT
                    ).show()
                )
                .setNegativeButton("Batal", null)
                .show();
        });

        space(24);
        button("LOGOUT", () -> login());
    }
  }
                          
