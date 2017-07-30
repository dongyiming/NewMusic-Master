package com.example.nc_super_abs.activity;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/5/23 12:49
 */
public class ActivityStack {
    private static Stack<Activity> activityStack;
    private static ActivityStack instance;

    private ActivityStack() {
    }

    public static ActivityStack getInstance() {
        if (instance == null) {
            Class var0 = ActivityStack.class;
            synchronized (ActivityStack.class) {
                if (instance == null) {
                    instance = new ActivityStack();
                    activityStack = new Stack();
                }
            }
        }

        return instance;
    }

    public int getCount() {
        return activityStack.size();
    }

    public <T extends Activity> void addActivity(T activity) {
        activityStack.add(activity);
    }

    public Activity topActivity() {
        if (activityStack == null) {
            throw new NullPointerException("Activity stack is Null,your Activity must extend BaseActivity");
        } else if (activityStack.isEmpty()) {
            return null;
        } else {
            Activity activity = (Activity) activityStack.lastElement();
            return activity;
        }
    }

    public <T extends Activity> Activity findActivity(Class<T> cls) {
        Activity activity = null;
        Iterator i$ = activityStack.iterator();

        while (i$.hasNext()) {
            Activity aty = (Activity) i$.next();
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }

        return activity;
    }

    public void finishActivity() {
        Activity activity = (Activity) activityStack.lastElement();
        this.finishActivity(activity);
    }

    public <T extends Activity> void finishActivity(T activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }

    }

    public <T extends Activity> void finishActivity(Class<T> cls) {
        Iterator i$ = activityStack.iterator();

        while (i$.hasNext()) {
            Activity activity = (Activity) i$.next();
            if (activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }

    }

    public void finishOthersActivity(Class<?> cls) {
        Iterator i$ = activityStack.iterator();

        while (i$.hasNext()) {
            Activity activity = (Activity) i$.next();
            if (!activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }

    }

    public void finishAllActivity() {
        int i = 0;

        for (int size = activityStack.size(); i < size; ++i) {
            if (null != activityStack.get(i)) {
                ((Activity) activityStack.get(i)).finish();
            }
        }

        activityStack.clear();
    }

    public <T extends Activity> boolean existsActivity(Class<T> activityClass) {
        for (int i = 0; i < activityStack.size(); ++i) {
            if (((Activity) activityStack.get(i)).getClass() == activityClass) {
                return true;
            }
        }

        return false;
    }

    public <T extends Activity> boolean finishActivityWhileTop(Class<T> exitActivityClass) {
        for (int i = 0; i < activityStack.size(); ++i) {
            if (((Activity) activityStack.get(i)).getClass() == exitActivityClass) {
                while (activityStack.size() != i) {
                    ((Activity) activityStack.get(i)).finish();
                    activityStack.remove(i);
                }

                return true;
            }
        }

        return false;
    }

    public <T extends Activity> int indexOfActivity(Class<T> activityClass) {
        for (int i = 0; i < activityStack.size(); ++i) {
            if (((Activity) activityStack.get(i)).getClass() == activityClass) {
                return i;
            }
        }

        return -1;
    }

    public <T extends Activity> int lastIndexOfActivity(Class<T> activityClass) {
        for (int i = activityStack.size() - 1; i >= 0; ++i) {
            if (((Activity) activityStack.get(i)).getClass() == activityClass) {
                return i;
            }
        }

        return -1;
    }

    public Stack<Activity> getStackActivities() {
        return activityStack;
    }

}
