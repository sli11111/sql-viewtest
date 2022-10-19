package com.ysmjjsy.goya.test;
class FooBar {
    private static int n;

    public FooBar(int n) {
        this.n = n;
    }


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            if (i%2==0){
                printFoo.run();
            }

        }
    }

    public static void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            if (i%2==1){
                printBar.run();
            }

        }
    }




}