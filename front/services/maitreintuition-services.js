class MaitreIntuitionServices {
    constructor() {}

    async getCartes(uniqueCode) {
        try {

          const storedData = localStorage.getItem(`cartes_${uniqueCode}`);
          if (storedData) {
            console.log("Données stockées trouvées:", storedData);
            return JSON.parse(storedData);
          }


          const response = await fetch(`http://127.0.0.1:8080/api/getCartesI/${uniqueCode}`,{
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }
          
          });
          if (!response.ok) {
            throw new Error('Erreur lors de la récupération des cartes');
          }
          const data = await response.json(); // Extraire les données JSON de la réponse

          
          if (!Array.isArray(data)) {
            throw new Error('La réponse de l\'API n\'est pas un tableau');
          }

          // Stocker les données dans sessionStorage pour une utilisation future
          localStorage.setItem(`cartes_${uniqueCode}`, JSON.stringify(data));

          return data;
        } catch (error) {
          console.error("Erreur dans getCartes:", error);
          return [];
        }
      }

      async submitCard(uniqueCode, position) {
        try {
            const response = await fetch(`http://127.0.0.1:8080/api/submitCard/${uniqueCode}/${position}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ position })
            });
            if (!response.ok) {
                throw new Error('Erreur lors de la soumission de la carte');
            }
            //Peut être des modifs à faire ici par la suite
            return response.ok;
        } catch (error) {
            console.error("Erreur dans submitCard:", error);
        }
    }
    async endTour(uniqueCode){
      try {
        const response = await fetch(`http://127.0.0.1:8080/api/endTour/${uniqueCode}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('Erreur lors de la fin du tour');
        }
        //Peut être des modifs à faire ici par la suite
        return response.ok;
      } catch (error) {
        console.error("Erreur dans endTour", error);
      }
    }
    async getPlayer1Name(uniqueCode) {
      try {
        const response = await fetch(`http://127.0.0.1:8080/api/getPlayer1Name/${uniqueCode}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
  
        if (!response.ok) {
          throw new Error('Erreur lors de la récupération du nom du joueur 1');
        }
  
        const data = await response.text();
        return data;
  
  }
  catch (error) {
    console.error("Erreur dans getPlayer1Name:", error);
    return "";
  }
  }
  
  async getPlayer2Name(uniqueCode) {
    try {
      const response = await fetch(`http:////127.0.0.1:8080/api/getPlayer2Name/${uniqueCode}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      });
  
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération du nom du joueur 2');
      }
  
      const data = await response.text();
      return data;
  
  }
  catch (error) {
  console.error("Erreur dans getPlayer2Name:", error);
  return "";
  }
  }
  async getInfo(uniqueCode) {
    try {
      const response = await fetch(`http://127.0.0.1:8080/api/getInfo/${uniqueCode}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      });
  
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des informations du jeu');
      }
  
      const data = await response.json();
      return data;
  
    } catch (error) {
      console.error('Erreur dans getInfo:', error);
      throw error; // Remonte l'erreur pour la gestion ultérieure
    }
  }
  async endTour(uniqueCode) {
    try {
      const response = await fetch(`http://127.0.0.1:8080/api/endTour/${uniqueCode}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (!response.ok) {
        throw new Error('Erreur lors de la fin du tour');
      }
      return response.ok;
    } catch (error) {
      console.error("Erreur dans endTour", error);
    }
  }
}

export { MaitreIntuitionServices };