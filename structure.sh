fileName="part"

read -r -d '' ktFile << EOF
package day_1

import readFile

fun part1Main() = readFile("src/day_1/input.txt")

fun main() {
    println(part1Main())
}

EOF

sed "s/1/2/g" "{$ktFile}" > "src/day_2/${fileName}1.kt"

#for (( i = 2; i < 3; i++ )); do
#    mkdir "src/day_${i}"
#    sed "s/1/${i}/g" "{$ktFile}" > "src/day_${i}/${fileName}1.kt"
#    sed "s/part1Main/part2Main/g" "${ktFile}" | sed "s/day_1/day_${i}/g" > "src/day_${i}/${fileName}2.kt"
#    touch "src/day_${i}/input.txt"
#done