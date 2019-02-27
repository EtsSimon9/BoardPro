# BoardPro
Bonjour, ici une liste de décision fait en équipe sur le fonctionnement de l'app:

27 fev: Fonctionnement fil
Pour ce qui considère les fils, si ils l'utilisateur décide de ne pas considérer
la résistance de ceux-ci, notre programme ne les considèrent pas non plus comme si il 
n'existait pas. Les objets Fil seront toutefois créer.
Si l'utilisateur veux considérer la résistance des fils, nous devront ramener ceux-ci à une résistance
équivalente

27 fev: Structure du circuit
Nous entrerons chaque composante dans une ArrayList d'Arraylist de composante. 

Idée 1: Chaque composante a une ArrayList<Composante> des composantes directement avant et une ArrayList<Composante> directement après.
Chaque fois qu'on rajoute une composante, on regarde dans la liste ComposanteAprès, si il n'y a rien le circuit n'est pas fermé, s'il y a une composante on continue. On regarde sa liste ComposanteAprès et ainsi de suite. Si au final une composante après est la composante 1 que nous avons ajouter à cette étape, le circuit est fermé. Nous devons donc faire des compareTo
  
  Une fois que nous avons un circuit fermé, nous commencons à remplir un ArrayList<ArrayList<ComposanteElectrique>> où l'ordre dépend
  de l'ordre du circuit, le premier élément est la dernière composante rajouté.
  
  Le deuxième élément de la double ArrayList est l'élément dans ListeAprès du premier. Si il y en a deux, le prochain élément du double ArrayList est celui à sa droite/gauche (horizontale). Le deuxième élément lui sera en dessous du premier dans l'ArrayList. Il est donc le 2e élément de la première ArrayList dans ArrayList<ArrayList<ComposanteÉlectrique>>.
  
  On continue avec le deuxième élément jusqu'à ce qu'on reviennet au première élément (on détecte ce moment avec les listesAprès). On revient avec le deuxième élément de l'ArrayList à la fin.
  
Une fois ArrayList<ArrayList<Composante>> terminé, nous devons finir chaque ArrayList de cette double ArrayList, c'est à dire finir les parties parralele. Pour le faire, on prend le deuxièmee élément, on regarde ce qu'il y a après, si c'est un fil, on compare ce fil au autre fil du circuit, si on trouve qu'il est identique à un autre, le travail est fini. Sinon, nous cherchons le prochain et fil et continuons le procéssus.
