from flask import Flask, request, jsonify
import subprocess
import logging
logging.basicConfig(level=logging.INFO)

app = Flask(__name__)

@app.route('/process_onnx', methods=['POST'])
def process_onnx():
    file_path = request.json.get('file_path')
    logging.info("Received file path: %s", file_path)
    file_path = '/'.join(file_path.split('/')[-2:])
    logging.info("Received onlyfile path: %s", file_path)
    result = subprocess.run(["python3", "onnx_read.py", file_path], capture_output=True, text=True)
    output_image_path = result.stdout.strip()
    return jsonify({"imagePath": output_image_path})

if __name__ == '__main__':
    app.run(host='XXXX', port=5000)

