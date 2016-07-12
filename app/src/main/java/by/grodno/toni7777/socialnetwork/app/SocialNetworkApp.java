package by.grodno.toni7777.socialnetwork.app;

import android.app.Application;

import by.grodno.toni7777.socialnetwork.network.NetworkService;

public class SocialNetworkApp extends Application {

    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService();
    }

    public NetworkService getNetworkService() {
        return networkService;
    }
}
