# Znanstveno računanje

Projekt prikazuje distribuirani sustav za znanstveno računanje temeljen na modelu Master-Worker. Sustav se sastoji od:
- jednog procesa **Master**
- više procesa **Worker**

Master dijeli zadatak na podzadatke, šalje ih radnicima i prikuplja rezultate te ih spaja u odgovor.

## Podržani računski zadaci:
- Traženje nultočaka neprekidne funkcije metodom bisekcije
- Faktorizacija cijelog broja

## POKRETANJE

### 1. Kompajliranje svih `.java` datoteka
```bash
javac *.java
```
### 2. Pokretanje Master procesa

Primjer: traženje nultočaka funkcije `java Master zero <N> <a> <b> <epsilon> [funkcija]`

- N – ukupan broj procesa (1 master + N-1 workera)
- a, b – granice intervala
- epsilon – tolerancija za metodu bisekcije
- funkcija - sin/exp/cubic

```bash
java Master zero 4 1.0 2.0 1e-6 sin
```

Primjer: faktorizacija broja -> `java Master factor <N> <broj>`

- N – ukupan broj procesa (1 master + N-1 workera)
- broj - broj koji želimo faktorizirati

```bash
java Master factor 4 13195
```

### 3. Pokretanje Workera u zasebnim terminalima

Primjer: za 3 workera je N = 4 ( 3 workera + 1 master )
```bash
java Worker 1 4
```
```bash
java Worker 2 4
```
```bash
java Worker 3 4
```