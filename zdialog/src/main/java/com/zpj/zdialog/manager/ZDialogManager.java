package com.zpj.zdialog.manager;

import com.zpj.zdialog.ZDialog;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ZDialogManager {

    private volatile boolean showing = false;//是否有dialog在展示
    private ConcurrentLinkedQueue<ZDialogWrapper> dialogQueue = new ConcurrentLinkedQueue<>();

    private ZDialogManager() {
    }

    public static ZDialogManager getInstance() {
        return DialogHolder.instance;
    }

    private static class DialogHolder {
        private static ZDialogManager instance = new ZDialogManager();
    }

    /**
     * 请求加入队列并展示
     *
     * @param zDialogWrapper ZDialogWrapper
     * @return 加入队列是否成功
     */
    public synchronized boolean requestShow(ZDialogWrapper zDialogWrapper) {
        boolean b = dialogQueue.offer(zDialogWrapper);
        checkAndDispatch();
        return b;
    }

    /**
     * 结束一次展示 并且检查下一个弹窗
     */
    public synchronized void over() {
        showing = false;
        next();
    }

    private synchronized void checkAndDispatch() {
        if (!showing) {
            next();
        }
    }

    /**
     * 弹出下一个弹窗
     */
    private synchronized void next() {
        ZDialogWrapper poll = dialogQueue.poll();
        if (poll == null) return;
        ZDialog dialog = poll.getDialog();
        if (dialog != null) {
            showing = true;
            dialog.show();
        }
    }


}
