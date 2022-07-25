package com.example.test0704;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    private class MyWebViewClient extends WebViewClient {
        @Override
       public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView1);

        //ScrollBar顯示在內容區域裡面,不會增加padding區域,該ScrollBar以半透明的樣式覆蓋在view的內容上
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //允許WebView使用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //是否開啟本地DOM儲存
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        //把圖片放在最後來加载渲染
        webView.getSettings().setBlockNetworkImage(false);
        //解除Android 5.0以上的WebView預設無法正常顯示混合式的網頁內容。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        //提供各種通知事件和請求事件，比如網頁開始加載，網頁加載結束，當然還有對連結的處理
        webView.setWebViewClient(new MyWebViewClient());
        //主要用於一些Web頁面中的彈出事件傳遞Native部分，可以使用自定義的形式進行顯示
        webView.setWebChromeClient(new MyWebChromeClient());

        webView.loadUrl("http://192.168.110.42:30000/");

    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}