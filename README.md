# TinyONNXViewer v1.0

demo ONNX files to upload : `./demoONNX/*.onnx`

This is a toy web application for purposes that draws especially **tiny** ONNX files (like onnx files in the `./demoONNX/` folder) with high accuracy. The difference from `Netron` (which is a famous tool that can draw onnx files so beautifully and fastly) is that this rendering is not layer by layer but unit by unit especially in Fully-connected layer, which enables visualization of more detailed information. Although for a general size of neural networks (like ResNet, AlexNet etc..) of 10 or more layers, drawing by this app is done layer by layer (and in this case, drawing is not at all beautiful), tiny onnx files can be drawn in detail. Further description in japanese are on my blog. (https://qiita.com/guardman/items/90584e0792ca13b4d325)

![demo image](./image/summary.png)

# Usage
Press the file selection button at the bottom of the UI to select an ONNX file, then press the `ONNX Upload` button to draw the uploaded ONNX file. non-ONNX files are not supported.

# Citation
netron : https://netron.app/


# Future-plan
There are many plans to improve Speed, visualization quality (we'll stop just rendering an image and planning to correspond to deal with larger onnx files), and planning to Introduce more information on this view like edge boldness in graph corresponding to its weight value etc..
