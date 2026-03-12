from S08_TP15 import *
import copy as cp

class Human(Element):

    def __init__(self):
        super().__init__("\U0001F9D1")

class Conway(PlanetTk):

    BACKGROUND_COLORS = ["#fffdd4", "#f5f3c4", "#fffdd9", "#f7f5c1"]
    FOREGROUND_COLORS = ["#42420a"]

    def __init__(self, lines_count, columns_count, name="Conway", cell_size = 30):
        """
        Initialise le jeu de la vie de Conway avec les paramètres spécifiés.

        Args:
            lines_count (int): Nombre de lignes de la planète.
            columns_count (int): Nombre de colonnes de la planète.
            cell_size (int): Taille des cellules de la planète (par défaut 30).
            name (str): Nom du jeu (par défaut "Conway").
        """
        super().__init__(root= tk.Tk(), name =name, authorized_classes = {Human, Ground}, lattitude_cells_count=lines_count,
                         longitude_cells_count=columns_count, cell_size= cell_size, background_color=self.BACKGROUND_COLORS,
                         foreground_color=self.FOREGROUND_COLORS)
        self.__lines_count = lines_count
        self.__columns_count = columns_count
        self.__generation_count = 0
        self.__simulation_on = False

        canvas = self.get_canvas()

        # Création de chaque case de la grille en fonction de __columns_count et __lines_count
        for cell_number in range(self.__columns_count * self.__lines_count):
            self.set_cell_color(cell_number, self.BACKGROUND_COLORS)
            self.set_on_cell_click(cell_number, self.invert_cell)

        self.__info_label = tk.Label(self.get_root(), text="Generation : 0\nPopulation : 0", justify="left", font="courier 12 bold", background=random.choice(self.BACKGROUND_COLORS), foreground=random.choice(self.FOREGROUND_COLORS))
        self.__info_label.place(relx = 0.02, rely = 0.02)

        canvas.focus_set()
        self.set_on_space_click(self.change_simultation)
        canvas.bind("<Right>", self.etape_suivant)
        self.get_canvas().pack()
        self.get_canvas().update()
        self.set_on_quit_click(self.quit)
        self.set_on_reset_click(self.reset)

    def change_simultation(self):
        """
        Change l'état de simulation (en cours ou arrêtée) et démarre ou arrête la boucle de simulation.

        Args:
            event (str): Événement de changement de simulation.
        """
        self.__simulation_on = not self.__simulation_on
        self.start_simualation()

    def start_simualation(self, event = None):
        """
        Boucle de simulation pour mettre à jour automatiquement le jeu.

        Args:
            event (str, optional): Événement de boucle de simulation (par défaut None).
        """
        if self.__simulation_on:
            self.etape_suivant()
            self.get_canvas().after(70, self.start_simualation)

    def etape_suivant(self, event = None):
        """
        Effectue une étape dans le jeu, mettant à jour la génération et l'état des cellules.

        Args:
            event (str, optional): Événement déclencheur (par défaut None).
        """
        if event != None and event.keysym == "Right" and self.__simulation_on:
            return

        self.__generation_count += 1
        grille = cp.deepcopy(self.get_grid())

        for cell_number in range(self.get_columns_count() * self.get_lines_count()):
            nb_voisins = self.get_living_neighbors_number(grille, cell_number=cell_number)
            i, j = self.get_coordinates_from_cell_number(cell_number)
            if isinstance(grille[i][j], Human):
                if nb_voisins > 3 or nb_voisins < 2:
                    self.die(cell_number)
                    self.set_cell_color(cell_number, random.choice(self.BACKGROUND_COLORS))
            else:
                if nb_voisins == 3:
                    self.born(cell_number, Human())
        self.__info_label.configure(text = f"Generation : {self.__generation_count}\nPopulation : {self.get_living_count()}")


    def get_living_neighbors_number(self, grille, cell_number, isTore = True):
        """
            Renvoie le nombre de voisins vivants d'une cellule donnée dans la grille.

            Args:
                grille (list): Grille de la planète.
                cell_number (int): Numéro de la cellule à vérifier.
                isTore (bool, optional): Indique si la grille est considérée comme un tore (par défaut True).

            Returns:
                int: Nombre de voisins vivants de la cellule.
            """
        voisins = self.get_cell_neighborhood_numbers(cell_number, self.WIND_ROSE, isTore)
        counter = 0
        for voisin in voisins:
            i, j = self.get_coordinates_from_cell_number(voisin)
            if isinstance(grille[i][j], Human):
                counter += 1
        return counter

    def get_living_count(self):
        """
        Renvoie le nombre d'êtres humains vivants sur la planète.

        Returns:
            int: Nombre d'êtres humains vivants.
        """
        count = 0
        for cell_number in range(self.__lines_count * self.__columns_count):
            if isinstance(self.get_cell(cell_number), Human):
                count += 1
        return count


    def invert_cell(self, cell_number):
        """
        Inverse l'état d'une cellule (humaine ou vide) lors d'un clic.

        Args:
            event (str): Événement de clic.
            cell_number (int): Numéro de la cellule à inverser.
        """
        if isinstance(self.get_cell(cell_number), Human):
            self.die(cell_number)
            self.set_cell_color(cell_number, self.BACKGROUND_COLORS)
        else:
            self.born(cell_number, Human(), self.FOREGROUND_COLORS)
        self.__info_label.configure(text = f"Generation : {self.__generation_count}\nPopulation : {self.get_living_count()}")

    def reset(self):
        """
        Réinitialise le jeu, remettant à zéro toutes les cellules.

        Args:
            event (str, optional): Événement de réinitialisation (par défaut None).
        """
        for cell in range(self.__lines_count * self.__columns_count):
            if self.is_instance_of(cell, Human):
                self.die(cell)
        self.__generation_count = 0
        self.__simulation_on = False
        self.__info_label.configure(text = f"Generation : {self.__generation_count}\nPopulation : {self.get_living_count()}")


if __name__ == "__main__":
    jeu_1 = Conway(22, 35, cell_size=25)
    jeu_1.start()




