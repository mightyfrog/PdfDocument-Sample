package org.mightyfrog.android.pdfdocumentsample

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

/**
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            generatePdf()
        }
    }

    private fun generatePdf() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels
        val h = displayMetrics.heightPixels
        PdfDocument().apply {
            val page = startPage(PdfDocument.PageInfo.Builder(w, h, 1).create())
            val view = findViewById<View>(android.R.id.content)
            view.draw(page.canvas)
            finishPage(page)
            FileOutputStream(File(Environment.getExternalStorageDirectory(), "test.pdf")).use {
                writeTo(it)
            }
        }
    }
}
