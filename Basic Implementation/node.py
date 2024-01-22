# Created: September 2022
class Node:
    def __init__(self, name, x, y):
        self.name = name
        self.x = x
        self.y = y
    def __repr__(self):
        return f"Node({self.name})"
