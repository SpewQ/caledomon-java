package model;

/**
 * Enumération des types du CalédoMon
 */
public enum Type {
    TERRESTRE,
    AERIEN,
    MARIN;

    public double effectivenessAgainst(Type other) {
        if (this == other) {
            return 1.0; 
        }

        switch (this) {
            case MARIN:
                
                if (other == TERRESTRE) return 0.5;
                
                if (other == AERIEN) return 2.0;
                break;

            case AERIEN:
                
                if (other == MARIN) return 0.5;
                
                if (other == TERRESTRE) return 2.0;
                break;

            case TERRESTRE:
                
                if (other == AERIEN) return 0.5;
                
                if (other == MARIN) return 2.0;
                break;
        }

        return 1.0; 
    }

    public boolean isAdvantagedAgainst(Type other) {
        return effectivenessAgainst(other) > 1.0;
    }

    public boolean isDisadvantagedAgainst(Type other) {
        return effectivenessAgainst(other) < 1.0;
    }
}
