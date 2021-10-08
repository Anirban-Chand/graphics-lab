import tkinter as tk

window_height = 800
window_width = 1200

# Initial zooming level
zoomlevel=10

class DrawPoint:
    x = window_width//2
    y = window_height//2

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def plot_point(self, color='black'):
        c.create_rectangle(
            self.x - 3,
            self.y - 3,
            self.x + 3,
            self.y + 3,
            fill=color,
            tag='temp_ending_point',
        )

    def plot_ending_point(self, color='blue'):
        c.create_oval(
            self.x - 5,
            self.y - 5,
            self.x + 5,
            self.y + 5,
            fill=color,
            tag='ending_point',
        )

class Plot_Shapes:
    def plot_rectangle(self,p1,p2):
        c.delete('temp_ending_point')
        c.delete('ending_point')

        e1=DrawPoint(p1.x, p1.y)
        e2=DrawPoint(p2.x, p1.y)
        e3=DrawPoint(p1.x, p2.y)
        e4=DrawPoint(p2.x, p2.y)

        
        u=e1.x
        v=e3.x
        while u<e2.x:
            DrawPoint(u, e1.y).plot_point()
            DrawPoint(v, e3.y).plot_point()
            u+=10
            v+=10
        
        u=e1.y
        v=e2.y
        while u<=e4.y:
            DrawPoint(e1.x, u).plot_point()
            DrawPoint(e2.x, v).plot_point()
            u+=10
            v+=10

        e1.plot_ending_point(color='red')
        e2.plot_ending_point(color='red')
        e3.plot_ending_point(color='red')
        e4.plot_ending_point(color='red')

        



# Grid generating method
def makeGrid(event=None):
    w = window_width 
    h = window_height 
    c.delete('grid_line') # Will only remove the grid_line
    c.delete('labels') # Will only remove the axes labels

    originx = round(w/2)
    originy = round(h/2)


    # Creates all vertical lines at intevals of 100
    for i in range(0, w, zoomlevel):
        if i == 600*(zoomlevel/10):
            c.create_line([(i, 0), (i, h)], tag='grid_line', fill='red', width=2)
        else:
            c.create_line([(i, 0), (i, h)], tag='grid_line', fill='green')
        # c.create_text(i,400*(zoomlevel/100), text=str((i//10)-w//20), tag='labels', font=('Helvetica', 12))
    
    # Creates all horizontal lines at intevals of 100
    for i in range(0, h, zoomlevel):
        if i == 400*(zoomlevel/10):
            c.create_line([(0, i), (w, i)], tag='grid_line', fill='red', width=2)
        else:
            c.create_line([(0, i), (w, i)], tag='grid_line', fill='green')
        # c.create_text(400*(zoomlevel/100),i, text=str((h//20)-(i//10)), tag='labels', font=('Helvetica', 12))


    # Starting Point - (x1, y1)
    x1 = round((originx-150) * (zoomlevel / 10))
    y1 = round((originy-150) * (zoomlevel / 10))

    # Ending Point - (x2, y2)
    x2 = round((originx + 150) * (zoomlevel / 10))
    y2 = round((originy + 150) * (zoomlevel / 10))

    print(f'Staring point = ({x1},{y1})')
    print(f'Ending point = ({x2},{y2})')

    start = DrawPoint(x1, y1)
    end = DrawPoint(x2, y2)
    Plot_Shapes().plot_rectangle(start,end)


# Zoom In methods
def zoomplus():
    global zoomlevel
    zoomlevel+=2
    makeGrid()
    

# Zoom Out methods
def zoomminus():
    global zoomlevel
    zoomlevel-=2
    makeGrid()


# Our main Tkinter window
root = tk.Tk()
root.title('Grid using Tkinter')  # setting title
c = tk.Canvas(root, height=window_height, width=window_width, bg='white')
c.pack(fill=tk.BOTH, expand=True)
c.bind('<Configure>', makeGrid)

# Zoom In and Zoom out Buttons
zoomin = tk.Button(text='Zoom In', command=zoomplus)
zoomin.place(x=20, y=20)  # placing zoom in button
zoomout = tk.Button(text='Zoom Out', command=zoomminus)
zoomout.place(x=20, y=60)  # placing zoom out button


# Run GUI Window
root.mainloop()