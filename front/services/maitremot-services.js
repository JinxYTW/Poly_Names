class MaitreMotServices {
  constructor() {
    
  }

  async getCartes(uniqueCode) {
    try {
      const storedData = localStorage.getItem(`cartes_${uniqueCode}`);
      if (storedData) {
        console.log("Données stockées trouvées:", storedData);
        return JSON.parse(storedData);
      }
      const response = await fetch(`http://127.0.0.1:8080/api/getCartesM/${uniqueCode}`,{
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      
      });
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des cartes');
      }
      const data = await response.json(); // Extraire les données JSON de la réponse
      console.log("Réponse API parsée:", data);
      console.log("Type de la réponse API parsée:", typeof data);
      if (!Array.isArray(data)) {
        throw new Error('La réponse de l\'API n\'est pas un tableau');
      }
      localStorage.setItem(`cartes_${uniqueCode}`, JSON.stringify(data));
      return data;
    } catch (error) {
      console.error("Erreur dans getCartes:", error);
      return [];
    }
  }

  async submitHint(indice, wordToFindNb, uniqueCode) {
    try {
      const response = await fetch(`http://127.0.0.1:8080/api/submitHint/${uniqueCode}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ indice, wordToFindNb })
      });

      if (!response.ok) {
        throw new Error('Erreur lors de l\'envoi de l\'indice');
      }

      const data = await response.text();
      return { status: "success", message: data };

    } catch (error) {
      console.error("Erreur dans submitHint:", error);
      return { status: "error" };
    }
  }

  //A corriger pour afficher pseudo

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
}

export { MaitreMotServices };