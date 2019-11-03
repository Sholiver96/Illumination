package com.sholiver.illumination.util;

public enum EnumTier {
        BASIC(0,1,0.8F),
        GLIMMERING(1,2,0.85F),
        LUMINOUS(2,4,0.9F),
        RADIANT(3,8,0.95F);

        private static int baseMachineLuminosity = 800;
        private static int baseSolarLensLuminosity = 200;
        private static int baseLaserLength = 4;

        private final int index;
        private final int multiplier;
        private final float efficiency;

        EnumTier(int index, int multiplier, float efficiency){
                this.index = index;
                this.multiplier = multiplier;
                this.efficiency = efficiency;
        }

        public int getMachineLuminosity(){
                return baseMachineLuminosity * multiplier;
        }
        public int getSolarLensLuminosity(){
                return baseSolarLensLuminosity * multiplier;
        }
        public int getLaserLength() {
                return baseLaserLength * multiplier;
        }
        public int getIndex(){
                return index;
        }
        public float getEfficiency(){
                return efficiency;
        }

        public static EnumTier fromIndex(int index){
                switch(index){
                        case 1: return GLIMMERING;
                        case 2: return LUMINOUS;
                        case 3: return RADIANT;
                        default: return BASIC;
                }
        }
}
