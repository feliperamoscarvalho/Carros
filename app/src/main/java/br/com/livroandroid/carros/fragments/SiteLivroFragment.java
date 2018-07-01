package br.com.livroandroid.carros.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.livroandroid.carros.R;
import livroandroid.lib.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteLivroFragment extends BaseFragment {

    private static final String URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm";
    private WebView webView;
    private ProgressBar progress;
    protected SwipeRefreshLayout swipeLayout;

    public SiteLivroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site_livro, container, false);
        webView = view.findViewById(R.id.webview);
        progress = view.findViewById(R.id.progress);
        setWebViewClient(webView);
        //Carrega a pagina
        webView.loadUrl(URL_SOBRE);

        //Swipe to Refresh
        swipeLayout = view.findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(OnRefreshListener());
        //Cores da animacao
        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);

        configJavascript();
        return view;
    }

    private void setWebViewClient(WebView webview) {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webview, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                // Liga o progress
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webview, String url) {
                // Desliga o progress
                progress.setVisibility(View.INVISIBLE);
                // Termina a animação do Swipe to Refresh
                swipeLayout.setRefreshing(false);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("livroandroid", "webview url: " + url);
                if (url != null && url.endsWith("sobre.htm")) {
                    AboutDialog.showAbout(getFragmentManager());
                    // Retorna true para informar que interceptamos o evento
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener(){
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();//Atualiza a pagina
            }
        };
    }

    private void configJavascript(){
        WebSettings settings = webView.getSettings();
        //Ativa o javascript da pagina
        settings.setJavaScriptEnabled(true);
        //Publica a interface para o Javascript
        webView.addJavascriptInterface(new LivroAndroidInterface(), "LivroAndroid");
    }

    class LivroAndroidInterface{
        @JavascriptInterface
        public void sobre(){
            toast("Clicou na figura do livro!");
        }
    }

}
