# RecyclerView Reuse Sample

Qiita記事のサンプルコードとして作成したアプリです。
RecyclerViewのViewHolder再利用による問題を実際に動かして確認できます。

## サンプルで確認できること

- RecyclerViewのViewHolderが再利用される様子
- `ClickListener`をリセットしなかった場合に発生する不具合
- `setOnClickListener(null)`でリセットした場合の挙動

## スクリーンショット

### 初期状態

<img src="images/initial.png" width="320">

初期状態ではItem 0〜9のみ削除可能です。

### ViewHolder再利用

<img src="images/reused.png" width="320">

スクロールするとViewHolderが別のアイテムに再利用されます。

赤色のセルは、
「前回表示していたアイテムはClick可能(true)だったが、
現在のアイテムはClick不可(false)になった」
ことを表しています。

### BUG Mode

<img src="images/bug_mode.png" width="320">

以前表示していたItemのClickListenerが残っているため、
現在のItemとは異なる処理が実行されます。

### FIXED Mode

<img src="images/fixed_mode.png" width="320">

ClickListenerをリセットしているため、不具合は発生しません。

## 動作モード

`MainActivity.kt`の`sampleMode`を書き換えることで切り替えられます。

現在のサンプルではUI上の切り替え機能は実装していません。
変更後に再ビルドしてください。

```kotlin
private val sampleMode = SampleMode.BUG
```

または

```kotlin
private val sampleMode = SampleMode.FIXED
```

変更後にアプリを再ビルドしてください。

| Mode  | 内容                          |
|-------|-----------------------------|
| BUG   | ClickListenerをリセットせず、不具合を再現 |
| FIXED | ClickListenerをリセットし、修正版を確認  |

## 動作環境

- Android Studio
- Kotlin
- RecyclerView
- ViewBinding

## ライセンス

MIT License

## 関連記事

- RecyclerViewのViewHolder再利用によるClickListenerのリセット漏れについて（Qiita公開後に記事URLを追加）
