# LoadStateView


## 使用
```JAVA
mLoadingView.withLoadedEmptyText("≥﹏≤ , 没有获取到订单 !")
            .withEmptyIco(R.drawable.order_empty)
            .withBtnEmptyEnnable(false)
            .withErrorIco(R.drawable.ic_chat_empty)
            .withLoadedErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !")
            .withbtnErrorText("去找回她!!!")
            .withLoadedNoNetText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走")
            .withNoNetIco(R.drawable.ic_chat_empty).withbtnNoNetText("网弄好了，重试")
            .withLoadingIco(R.drawable.loading_animation)
            .withLoadingText("加载中...")
            .withOnRetryListener(new OnRetryListener() {
                @Override
                public void onRetry(LoadingState state) {
                      initData();
                }
            }).build();
```

```JAVA
//加载后空白内容提示文字，图片并设置无retry按钮
.withLoadedEmptyText("≥﹏≤ , 没有获取到订单 !")
.withEmptyIco(R.drawable.order_empty) 
.withBtnEmptyEnnable(false)
```
```JAVA
//加载错误提示文字，图片和retry按钮文字
.withLoadedErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !")
.withErrorIco(R.drawable.ic_chat_empty)
.withbtnErrorText("去找回她!!!")
```	
```JAVA
//无网络时提示文字，图片和retry按钮文字
.withLoadedNoNetText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走")
.withNoNetIco(R.drawable.ic_chat_empty)
.withbtnNoNetText("网弄好了，重试")
```
```JAVA
//加载中的文字和图片，(resource里的animation-list，gif都可以)
.withLoadingIco(R.drawable.loading_animation)
.withLoadingText("加载中...")
```
```JAVA
//retry按钮监听
.withOnRetryListener(new OnRetryListener() {
                @Override
                public void onRetry(LoadingState state) {
                      initData();
                }
            })
```
```java
//初始化，不带参数使用默认参数，详细看代码
.build();
```
  
