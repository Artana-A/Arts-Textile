package com.example.textileandro040426

object TextileData {
    val listTextile: ArrayList<Textile>
        get() {
            val list = arrayListOf<Textile>()

            // Cotton
            list.add(Textile(
                image = R.drawable.cot_poplin,
                id = 1,
                name = "Poplin",
                price = 58000,
                type = TextileType.Cotton,
                stock = 100
            ))
            list.add(Textile(
                image = R.drawable.cot_gauze,
                id = 2,
                name = "Gauze",
                price = 65000,
                type = TextileType.Cotton,
                stock = 100
            ))

            // Silk
            list.add(Textile(
                image = R.drawable.silk_organza,
                id = 3,
                name = "Organza",
                price = 145000,
                type = TextileType.Silk,
                stock = 100
            ))
            list.add(Textile(
                image = R.drawable.silk_satin,
                id = 4,
                name = "Satin",
                price = 195000,
                type = TextileType.Silk,
                stock = 100
            ))

            // Polyester
            list.add(Textile(
                image = R.drawable.poly_ceruti,
                id = 5,
                name = "Ceruti",
                price = 38000,
                type = TextileType.Polyester,
                stock = 100
            ))
            list.add(Textile(
                image = R.drawable.poly_sifon,
                id = 6,
                name = "Sifon",
                price = 32000,
                type = TextileType.Polyester,
                stock = 100
            ))

            return list
        }
}