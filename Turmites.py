import random
import tkinter as tk
from S08_TP15 import *
import copy
#creer copy d'un variable d'un objet
class Turmite(Element):
    """Représente une fourmi de Langton sur la planète."""
    UP, DOWN, LEFT, RIGHT = 0, 1, 2, 3

    def __init__(self, color):
        """
        Initialise une fourmi de Langton avec une couleur donnée.

        Args:
            color (str): Couleur de la fourmi.
        """
        super().__init__("\U0001F41C")
        #
        self.__color = color
        self.__direction = Turmite.UP
        self.__position = None

    def get_color(self):
        return self.__color

    def get_direction(self):
        return self.__direction

    def set_direction(self, direction):
        self.__direction = direction

    def set_position(self, position):
        self.__position = position

    def get_position(self):
        return self.__position

class Turmites(PlanetTk):
    """Représente le jeu de Langton avec plusieurs fourmis sur une planète cette."""
    #cette classe va gerer la logique du jeu et le mouvemet des fourmis et l'interface du graphique
    BACKGROUND_COLORS = ["#91797f", "#a8828c", "#a69096"]
    FOREGROUND_COLORS = named_colors = [
        "AliceBlue", "AntiqueWhite", "Aqua", "Aquamarine", "Azure", "Beige", "Bisque", "Black", "BlanchedAlmond", "Blue",
        "BlueViolet", "Brown", "BurlyWood", "CadetBlue", "Chartreuse", "Chocolate", "Coral", "CornflowerBlue", "Cornsilk",
        "Crimson", "Cyan", "DarkBlue", "DarkCyan", "DarkGoldenrod", "DarkGray", "DarkGreen", "DarkKhaki", "DarkMagenta",
        "DarkOliveGreen", "DarkOrange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue", "DarkSlateGray",
        "DarkTurquoise", "DarkViolet", "DeepPink", "DeepSkyBlue", "DimGray", "DodgerBlue", "Firebrick", "FloralWhite",
        "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold", "Goldenrod", "Gray", "Green", "GreenYellow", "Honeydew",
        "HotPink", "IndianRed", "Indigo", "Ivory", "Khaki", "Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon",
        "LightBlue", "LightCoral", "LightCyan", "LightGoldenrodYellow", "LightGray", "LightGreen", "LightPink", "LightSalmon",
        "LightSeaGreen", "LightSkyBlue", "LightSlateGray", "LightSteelBlue", "LightYellow", "Lime", "LimeGreen", "Linen",
        "Magenta", "Maroon", "MediumAquamarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen", "MediumSlateBlue",
        "MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream", "MistyRose", "Moccasin",
        "NavajoWhite", "Navy", "OldLace", "Olive", "OliveDrab", "Orange", "OrangeRed", "Orchid", "PaleGoldenrod", "PaleGreen",
        "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru", "Pink", "Plum", "PowderBlue", "Purple", "Red",
        "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon", "SandyBrown", "SeaGreen", "SeaShell", "Sienna", "Silver", "SkyBlue",
        "SlateBlue", "SlateGray", "Snow", "SpringGreen", "SteelBlue", "Tan", "Teal", "Thistle", "Tomato", "Turquoise", "Violet",
        "Wheat", "White", "WhiteSmoke", "Yellow", "YellowGreen"
    ]


    def __init__(self, lines_count, columns_count, cell_size = 30, speed = 100):
        """
         Initialise le jeu de Langton avec les paramètres spécifiés.

         Args:
             lines_count (int): Nombre de lignes de la planète.
             columns_count (int): Nombre de colonnes de la planète.
             cell_size (int): Taille des cellules de la planète (par défaut 30).
             speed (int): Vitesse de déplacement des fourmis (par défaut 100).
         """
        #creation  de la fenetre Tk qui configure la grille
        super().__init__(root=tk.Tk(), name ="Turmites", lattitude_cells_count= lines_count,  longitude_cells_count=columns_count, authorized_classes={Turmite},
                         cell_size = cell_size, gutter_size=0, background_color= self.BACKGROUND_COLORS)
        #authorized classes authorise la classe Turmmite comme l'élément de la planète
        #gutter_size taille de la marge entre cellule
        self.__lines_count = lines_count #stocker le nombre de lines et columns dans un attibut
        self.__columns_count = columns_count
        self.__is_playing = False #initialise un attribut pour suivre si le jeu est en cours ou non
        self.__speed = speed
        #pour differencié chaque fourmi

        canvas = self.get_canvas()
        #manipulation des canvas
        for cell_number in range(self.__columns_count * self.__lines_count):
            self.set_cell_color(cell_number, self.BACKGROUND_COLORS)
            #Initialisation des couleurs de fond des cellules :

            self.set_on_cell_click(cell_number, self.spawn_ant)
            #Gestion des clics sur les cellules :
        canvas.bind("<space>", lambda event: self.change_simulation())
        canvas.bind("<Right>", lambda event : self.button_right())
        #La méthode bind() est utilisée pour lier les événements clavier à des fonctions spécifiques.
        # Lorsque la touche <space> est pressée, la méthode change_simulation() est appelée, et
        # lorsque la touche <Right> est pressée, la méthode button_right() est appelée.
        # Ces liaisons clavier permettent de contrôler l'application en utilisant le clavier.
        canvas.focus_set()
        # définir le focus sur le canvas. Cela signifie que le canvas recevra les événements du clavier,
        # ce qui est nécessaire pour que les liaisons clavier fonctionnent correctement.

        canvas.pack()#affiche la planete

        canvas.update()#mets à jour l'interface graphique
        self.set_on_quit_click(self.quit)
        self.set_on_reset_click(self.reset)

    def change_simulation(self):
        """
        Change l'état de simulation (en cours ou arrêtée) et démarre ou arrête la boucle de simulation.
        """
        self.__is_playing = not self.__is_playing
        for cell in self.get_classes_cell_number()['Turmite']:
            self.repeat_movements(self.get_cell(cell))

    def repeat_movements(self,  ant: Turmite, event = None):
        """
        Répète les mouvements de la fourmi en continu lors de la simulation.

        Args:
            ant (Turmite): Instance de la fourmi.
            event (str, optional): Événement associé (par défaut None).
        """
        if self.__is_playing:
            self.move_ant(ant)
            self.get_canvas().after(self.__speed, lambda : self.repeat_movements(ant))

    def button_right(self):
        """
        Effectue une étape de simulation lors de l'appui sur la touche droite.
        """
        if not self.__is_playing:
            for cell in self.get_classes_cell_number()['Turmite']:
                self.move_ant(self.get_cell(cell))

    def move_ant(self, ant: Turmite):

        """
        Déplace une fourmi en fonction de sa couleur actuelle et met à jour la planète.

        Args:
            ant (Turmite): Instance de la fourmi à déplacer.
        """


        #au début j'avais creer une variable gobal qui stocker la position et la direction actutulle du fourmi(supprimer cettte varible).
        #changer la function move ant au lieu de faire bouger la fourmi global il va prendre directement dans les parametres (ant)
        #en fonctionn de ce qu'il a comme attribut il va caluler son prochaine position et la faire deplacer

        current_postion = copy.deepcopy(ant.get_position())
        next_position = self.next_position_of(ant)
        color = self.get_cell_color(current_postion)
        self.move_element(current_postion, next_position, self.get_cell_color(next_position))

        if color == ant.get_color():
            self.set_cell_color(current_postion, self.BACKGROUND_COLORS)
        else:
            self.set_cell_color(current_postion, ant.get_color())
            print(self)

    def next_position_of(self, ant : Turmite):
        """
        Calcule la prochaine position d'une fourmi en fonction de sa couleur et de sa direction actuelle.

        Args:
            ant (Turmite): Instance de la fourmi.

        Returns:
            int: Numéro de la cellule où la fourmi se déplacera.
        """
        direction = ant.get_direction()#direction actuelle de la fourmi

        i, j = self.get_coordinates_from_cell_number(ant.get_position())#récupère les coordonèes de la fourmi sur la cellule actuelle
        color = self.get_cell_color(ant.get_position())
        if direction == Turmite.UP:
            #calculer en fonction de la couleur et la direction de la fourmi
            #ce code définit les règles de déplacement de la fourmi en fonction de la couleur de la cellule sur laquelle elle se trouve.
            if color == ant.get_color():
                ant.set_direction(Turmite.LEFT)
                j -= 1
            else:
                ant.set_direction(Turmite.RIGHT)
                j += 1

        elif direction == Turmite.DOWN:
            if color == ant.get_color():
                ant.set_direction(Turmite.RIGHT)
                j += 1
            else:
                ant.set_direction(Turmite.LEFT)
                j -= 1
        elif direction == Turmite.LEFT:
            if color == ant.get_color():
                ant.set_direction(Turmite.DOWN)
                i += 1
            else:
                ant.set_direction(Turmite.UP)
                i -= 1
        elif direction == Turmite.RIGHT:
            if color == ant.get_color():
                ant.set_direction(Turmite.UP)
                i -= 1
            else:
                ant.set_direction(Turmite.DOWN)
                i += 1


        i %= self.get_lines_count()
        j %= self.get_columns_count()
        #Si la fourmi atteint le bord de la grille, elle réapparaît de l'autre côté (grâce à l'opérateur modulo
        ant.set_position(self.get_cell_number_from_coordinates(i, j))
        #Met à jour la position de la fourmi avec les nouvelles coordonnées calculées.
        return self.get_cell_number_from_coordinates(i, j)
        # Retourne le numéro de la cellule où la fourmi se déplacera.


    def spawn_ant(self, cell_number):
        """
        Fait apparaître une nouvelle fourmi ou la supprime lors d'un clic sur une cellule.

        Args:
            event (str): Événement de clic.
            cell_number (int): Numéro de la cellule cliquée.
        """
        if isinstance(self.get_cell(cell_number), Turmite):#funtion d'une case vérifie le type d'une case
            self.die(cell_number)
        #vérifie si un objet est une instance d'une classe donnée.
        # Si c'est le cas, cela signifie qu'une fourmi est déjà présente dans cette cellule.
        else:
            turmite = Turmite(random.choice(self.FOREGROUND_COLORS))
            #sinon si la cellule est vide cad ne contient pas de cellule
            #creer une une nouvelle instance de turmite avec un couleur aleatoire choisi pari les coleur defini dans le foreground
            turmite.set_position(cell_number)
            #defini la position de la nouvelle fourmi sur la cellule spécifié
            self.born(cell_number, turmite, turmite.get_color())


    def reset(self):
        """
        Réinitialise le jeu en remettant à zéro toutes les cellules et les fourmis.
        """
        for cell in range(self.__lines_count * self.__columns_count):
            self.__is_playing = False
            self.set_cell_color(cell, self.BACKGROUND_COLORS)
            if self.is_instance_of(cell, Turmite):
                self.die(cell)

if __name__ == "__main__":
    jeu = Turmites(25, 45, 20, speed = 15)
    jeu.start()