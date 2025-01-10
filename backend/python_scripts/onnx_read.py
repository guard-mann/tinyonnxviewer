import onnx
from graphviz import Digraph
from tqdm import tqdm
import sys

def read_onnx(model_path):
  # ONNXモデルの読み込み
 
  model = onnx.load(model_path)

  # グラフの取得
  graph = model.graph

  # ノードの情報を表示
  inputnode = []
  outputnode = []
  attribute = []

  for node in tqdm(graph.node):
    if node.op_type == "Gemm": # 全結合層
      weight_name = node.input[1]
      for initializer in graph.initializer:
        if initializer.name == weight_name:
          weight_shape = initializer.dims
          # 入力チャネル数と出力チャネル数
          input_channels = weight_shape[1]  # 重みの形状の2番目の次元（列)
          output_channels = weight_shape[0]  # 重みの形状の2番目の次元（列)
      gemm_inputside_idx = []
      for inputnode_idx in range(input_channels):
        inputnode.append(node.input)
        gemm_inputside_idx.append(f"{node.output[0]}_in_{inputnode_idx}")
        attribute.append("UNIT")
      outputnode.extend(gemm_inputside_idx)

      gemm_outputside_idx = []
      for outputnode_idx in range(output_channels):
        inputnode.append(gemm_inputside_idx)
        gemm_outputside_idx.append(f"{node.output[0]}_out_{outputnode_idx}") 
        attribute.append("UNIT")
      outputnode.extend(gemm_outputside_idx)
      
      inputnode.append(gemm_outputside_idx)
      outputnode.append(node.output[0])
      attribute.append("CONNECT_UNIT")
            
    else:
     inputnode.append(node.input)    
     outputnode.append(node.output[0])
     attribute.append(node.op_type)
     
  edges = []
  for node_idx in tqdm( range(len(outputnode)) ):
    for node in inputnode[node_idx]:
      if node in outputnode:
        edges.append([outputnode.index(node), node_idx])

  # グラフの作成
  dot = Digraph(format='png')
  dot.graph_attr['dpi'] = '300'
  dot.attr('node', shape='box')  # 全てのノードを四角形に設定
  dot.attr(rankdir='LR', size='8,5')  # 'rankdir' はレイアウトの方向、'LR' は左から右へ

  for idx, label in enumerate(attribute):
    if attribute[idx] == "UNIT":
      color= "grey"
      shape = "circle"
      label_node = ""
      width="0.5"
      height="0.5"
    elif attribute[idx] == "CONNECT_UNIT":
      color="black"
      shape="circle"
      width="0.1"
      height="0.1"
      
    else:
      color="cyan"
      shape = "box"
      label_node = attribute[idx]
      width="2"
      height="2"
     
    
    dot.node(str(idx), label=label_node, shape=shape, width=width, height=height, style='filled', fillcolor=color, fontname='Arial', fontsize='20')
  # ノードとエッジの追加
  for edge in edges:
    # ノードは自動的に作成されるので、エッジのみを追加
    dot.edge(str(edge[0]), str(edge[1]))

  # グラフを表示するためのファイルの保存とレンダリング
  output_path = 'path/to/your/file'
  dot.render(output_path, format='png')

if __name__ == "__main__":
  file_path = sys.argv[1]
  read_onnx(file_path)
