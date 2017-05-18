# Android - desenvolvimento mobile

## Aula 1

### Tópicos:

  - [História](#historia)
  - [API's](#apis)
  - [Arquitetura](#arquitetura)
    - [Dalvik](#dalvik)
    - [Dalvik vs ART](#dalvikvsart)
  - [Android Studio](#androidstudio)
    - [O ambiente de desenvolvimento](#studioconfig)
    - Gerênciador de SDk's
    - Emuladores
    - Importar projeto do Esclipse ADT
    - Estrutura de projeto
  - Layout
    - RelativeLayout
    - LinearLayout
    - ImageView
    - ButtonView e click event
    - Gravity

---

<a name="historia"/>

### História

O Android não foi criado pela Google, pasme! Em 2003 a Android Inc. estava criando um sistema operacional com grande epotêncial, porém estava sendo desenvolvido para câmeras digitais... Com esse potêncial sendo cada vez mais aparente, uma equipe de desenvolvedores começou a importar tal sistema para celulares numa era onde [Symbian](https://en.wikipedia.org/wiki/Symbian) e [Windows Mobile](https://en.wikipedia.org/wiki/Windows_Mobile) dominavam o mercado.

Em 2005 a Google comprou a Android Inc. por $50 milhões para colocar em celulares Blackberry... Tente imaginar esse mundo... Não havia touch e o teclado era o QWERTY.

Quando o IPhone veio ao mercado os engenheiros da equipe Android perceberam que precisavam voltar para a mesa de projetos... Hahah... Logo em 2008 surge a nova cara do Android rodando em um [HTC Dream](https://en.wikipedia.org/wiki/HTC_Dream), com incríveis 256Mb de RAM.

**Link interessante sobre a história:** https://en.wikipedia.org/wiki/Android_(operating_system)

<br>

<a name="apis"/>

### API'S

Até agora são 25 API's, da qual a última versão é o Android 7.1 (Nougat) que representa menos de 1% do mercado de telefones ativos. Foram poucas vezes que houve uma grande mudança no sistema operacional. Foi no Android 5.0 onde ocorreu a maior mudança no sistema operacional, mudando tanto seu funcionamento, quanto a identidade visual, pois adicionaram um novo sistema de compilação ([ART](https://source.android.com/devices/tech/dalvik/)) e a implementação do [Material Design](https://material.io/guidelines/).

Na tabela a baixo consta algumas versões do Android e suas features, junto com a porcentagem de dispositivos que irá conseguir rodar seu aplicativo (API 16 é a ideal para deixar como mínimo, pois tem um bom alcance, assim como grande parte das bibliotecas de terceiros irá funcionar com essa API).

| Versão | API   | Nome  | Ano | Novidades                                                  |  Alcance (%) |
| ------ |:----:| :-----:|:----:| :--------------------------------------------------------:| ------:|
| 1.6    | 4 | Donut | 2009   |  <ul><li>Diferentes tamanhos de tela</li><li>Google Play</li><li>Pesquisa rápida</li></ul>                                                                            | 100 |
| 2.1    | 5 | Eclair | 2009  |  <ul><li>Google Maps</li><li>Speech to Text</li></ul>        | 100 |
| 2.2    | 8 | Froyo | 2010   |  <ul><li>Tethering</li><li><a href = "https://android-developers.googleblog.com/2010/05/dalvik-jit.html"><b>Dalvik JIT</a></b></li></ul>       | 100 |
| 2.3    | 9 | Gingerbread | 2010  |  <ul><li>NFC</li><li>Suporte ao FLASH :laughing:</li></ul>        | 100 |
| 3.0    | 11 | Honeycomb | 2011   |  <ul><li>Adição da Navbar</li><li>Suporte multicore CPU</li></ul>       | 97.4 |
| 4.0    | 14 | Icecream sandwich | 2011  |  <ul><li>Gerenciador de dados móveis</li><li>Screenshot</li><li>Desbloqueio por reconhecimento facial</li></ul>        | 97.4 |
| 4.1    | 16 | Jellybean | 2012   |  <ul><li>Google now</li><li>Múltiplas contas no celular</li><li><b>Maioria das bibliotecas de terceiros suportam até essa versão (bom para sdk mínima)</b></li></ul>       | 95.2 | <!-- caso mude, mudar no configurando android studio -->
| 4.4    | 19 | Kitkat | 2013  |  <ul><li>"OK GOOGLE..." (Google começa a escutar tudo)</li><li>Sistema mais estável</li></ul>        | 73.9 |
| 5.0    | 21 | Lollipop | 2014   |  <ul><li>Suporte para CPU 64bit</li><li><b><a href = "https://material.io/guidelines/">Material Design</a></b></li><li>Android Watch, Car, TV</li><li><a href = "https://source.android.com/devices/tech/dalvik/"><b>ART AOT</b></a></li></ul>       | 40.5 |
| 6.0    | 23 | Marshmallow | 2015  |  <ul><li>Suporte à telas 4K</li><li>Novo sistema de permissões :imp:</li></ul>        | 4.7 |
| 7.0    | 24 | Nougat | 2016  |  <ul><li>**ART (JIT + AOT)**</li><li>[Vulcan 3D](https://en.wikipedia.org/wiki/Vulkan_(API))</li></ul>        | < 1 |

**Link bem legal da história do Android:** https://www.android.com/history/

**Lista completa de features por versão:** https://en.wikipedia.org/wiki/Android_version_history

<br>

<a name="arquitetura"/>

### Arquitetura

Atualmente a arquitetura do Android se organiza desta forma:

<p align="center"><img src="https://developer.android.com/guide/platform/images/android-stack_2x.png" width="450" height="667" /></p>

- **Apps**
> É nessa camada que seu app será instalado, assim como app de terceiros e apps de sistema.
- **Java API Framework**
> Métodos do framework para uso dentro de um app, tendo métodos públicos e privados (dos quais são acessíveis por [Java Reflection](https://docs.oracle.com/javase/tutorial/reflect/) ou [Hidden API](https://github.com/anggrayudi/android-hidden-api)).
- **C/C++**
> Essa camada é especialmente útil para métodos que necessitam de alta performance (jogos, algoritmos complexos, filtros, etc..), pois é a última camada acessível dentro de um aplicativo e não passa pelo Java, por conseguinte não precisa passar pelo compilador do Android. Essa camada é também conhecida por [**NDK**](https://developer.android.com/ndk/index.html).
- **Android Runtime**
> Nessa camada se encontra o compilador de APK -> bytecode -> dex (dex2jar), tendo dois tipos de compiladores: Dalvik e ART.
- **HAL**
> A camada de abstração de hardware (Hardware Abstraction Layer) cuida dos drivers que o dispositivo irá usar, sem que as camadas acima tenham que se preocupar em se comunicar diretamente com um componente, como a câmera por exemplo.
- **Kernel**
> Agrega e organiza os drivers, modo de boot, carrega programas primários do sistema ([ADB](https://developer.android.com/studio/command-line/adb.html), [zygote](https://anatomyofandroid.com/2013/10/15/zygote/), etc).

<br>

<a name="dalvik"/>

#### Dalvik - Android Runtime

[Dalvik](https://en.wikipedia.org/wiki/Dalvik_(software)) faz parte do Android Runtime, do qual inclui multiplas classes Java em um único arquivo `.dex` (Dalvik executable), junto com algumas outras instruções úteis para o funcionamento da VM. Normalmente um arquivo `.dex` descomprimido ocupa menos espaço que um `.jar` comprimido.

Essa arquitetura é mais coplexa que uma [máquina virutal Java comum](https://en.wikipedia.org/wiki/Java_virtual_machine), porém usa menos espaço em disco que o convencional, é mais rápida e é otimizada para funcionar com pouca memória.

Em 2010 a Oracle processou a Google por ter quebrado licenças da VM Java, mas óbviamente os advogados da Google são bons o sufiente pra não perderem o caso e sairem ilesos haha...

<a name="dalvikvsart"/>

#### Dalvik vs ART

A arquitetura ART nada mais é, que uma atualização do Dalvik, introduzido no Android 5.0 (Lollipop), diferente da arquitetura JIT (Just In Time - Apenas no momento) do Dalvik a arquitetura de compilação [ART](https://source.android.com/devices/tech/dalvik/) funciona com o princípio AOT (Ahead Of Time - À frente do tempo) em conjunto com JIT para manter compatibilidade, o que lhe provê uma maior performance, utilizando menos RAM e tendo menos chamadas de [Garbage Colector](https://www.dynatrace.com/resources/ebooks/javabook/how-garbage-collection-works/). Em outras palavras, quando você instala um app no dispositvo com ART runtime AOT, todo o app já é compilado para o uso, por isso que demora da primeira vez (dex2oat). Diferente do Dalvik, que vai compilando classes Java conforme é necessário.

Em 2016 a equipe do Android Developer anunciou no Google IO uma arquitetura híbrida (JIT + AOT) de compilação por perfil (PGC - [Profile Guided Compilation](https://www.slideshare.net/linaroorg/las16201-art-jit-in-android-n)) para o Android 7+ (Nougat), mostrando melhorias de performance e redução do espaço usado por aplicações em disco.

- Esse é um vídeo explicando o PGC: https://www.youtube.com/watch?v=fwMM6g7wpQ8
- Enquanto esse explica sobre o ART: https://www.youtube.com/watch?v=EBlTzQsUoOw
- E caso esteja **MUITO** afim de aprender sobre VMs Android (Dalvik): https://www.youtube.com/watch?v=ptjedOZEXPM hahahahh...

Só aí já tem quase um filme do Senhor dos Aneis versão extendida em questão de tempo... Minha dica: sai programando e deixa esses rodando vídeos no fundo haha...

<p align="center"><img src="https://developer.android.com/images/tools/studio/build-process_2x.png" width="450" height="505" /></p>

**Caso queira conhecer mais a fundo o esqueleto do Android:** https://source.android.com/devices/tech/

<br>

<a name="androidstudio"/>

### Android Studio

O Android Studio é um abiente de desenvolvimento integrado (IDE - Integrated Development Enviroment) para desenvolver aplicações para celulares, relógios, tv's e carros que rodam Android. Essa IDE é baseada no [IntelliJ](https://www.jetbrains.com/idea/) da JetBrains, substituindo assim o, já descontinuado :pray:, ambiente de desenvolvimento no [Eclipse ADT](https://developer.android.com/studio/tools/sdk/eclipse-adt.html).

**Link oficial para baixar o Android Studio _(Linux, Mac, Windows)_:** https://developer.android.com/studio/index.html

Assim que a IDE é instalada, existe algumas configurações e plugins que irão facilitar sua vida na hora que estiver programando.

<a name="studioconfig"/>

#### Configurando o Android Studio

<p align="center"><img src="https://media.giphy.com/media/xUA7aYaOm07Tqvqyuk/giphy.gif" /></p>
<!-- trocar para gif -->

- Inicie um novo projeto (primeiro ícone)
- Escolha o nome do seu projeto (nesse caso dei o nome de Appaula01)
- Diga o nome do package padrão do projeto (deixei como nicolas.schirmer)
- Selecione o alcance mínimo de API (minha dica é deixar a API 16 como mínimo, devido à alta compatibilidade com bibliotecas de terceiros e grande alcance de dispositivos, representando 95.2% dos dispositivos ativos)
- Por conseguinte, selecione a "Empty Activity" para começar um projeto limpo
- Caso queira mudar o nome da Activity, sinta-se à vontade (irei deixar no padrão `MainActivity`)
- **Finish**

**Pronto!** Agora que temos um projeto recém nascido e sem atributos, podemos deixá-lo de lado por enquanto para analisar e fazer possíveis melhorias no Android Studio, afinal, em algum momento você tem que se dar bem e se sentir confortável com esse negócio.



