class MaitreMotServices {
  constructor() {
    
  }

  async getCartes(uniqueCode) {
    try {
      const response = await fetch(`http://127.0.0.1:8080/api/getCartes/${uniqueCode}`,{
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
      return data;
    } catch (error) {
      console.error("Erreur dans getCartes:", error);
      return [];
    }
  }

}

export { MaitreMotServices };