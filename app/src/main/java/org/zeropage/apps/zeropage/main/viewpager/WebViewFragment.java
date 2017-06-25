package org.zeropage.apps.zeropage.main.viewpager;

import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by gnidoc327 on 2017. 6. 25..
 */

public class WebViewFragment extends Fragment {
    protected WebView webView;

    public WebViewFragment() {
    }

    public void initWebView(View view, int id, String url){
        webView = (WebView) view.findViewById(id);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
    }

    public boolean canGoBack() {
        return webView != null && webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }
}
