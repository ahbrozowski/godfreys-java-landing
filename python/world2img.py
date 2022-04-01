
from PIL import Image, ImageMode
import sys

if len(sys.argv) < 3:
    print("Expected a filename 'world2img <world-file> <img-file>")

fn = sys.argv[1]

imgFn = sys.argv[2]


with open(fn, "rb") as f:
    spawnY = f.read(4)
    xLen = int.from_bytes(f.read(4), "big")
    yLen = int.from_bytes(f.read(4), "big")
    data = f.read(-1)

data = bytes([d*28 for d in data])

print(f"xLen = {xLen}")
print(f"yLen = {yLen}")
print(f"data = {len(data)}")


img = Image.frombytes("P", (xLen, yLen), data, "raw", "P", 0, 1)
img = img.convert()
img = img.rotate(270)

img.save(imgFn, format="PNG")
