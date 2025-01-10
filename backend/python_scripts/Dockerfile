# Pythonの公式イメージをベースにする（バージョンは必要に応じて調整）
FROM python:3.12-slim

# Graphvizのインストール
RUN apt-get update && apt-get install -y graphviz

# PythonのGraphvizライブラリをインストール
RUN pip install graphviz onnx flask tqdm boto3

# 作業ディレクトリを設定
WORKDIR /app

# 必要なPythonスクリプトやファイルをコンテナにコピー
COPY . .

CMD ["python3", "app.py"]
