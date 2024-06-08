class MaitreMotServices {
  constructor() {
    
  }

  async getCartes(uniqueCode) {
    try {
      const response = await fetch(`/api/getCartes/${uniqueCode}`);
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des cartes');
      }
      return await response.json();
    } catch (error) {
      console.error(error);
      return [];
    }
  }

}

export { MaitreMotServices };