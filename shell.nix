with import (fetchTarball https://github.com/NixOS/nixpkgs-channels/tarball/fb1bc1b891f5c95b98a333e46b043018ff50d254) {};

stdenv.mkDerivation {
  name = "contact-book-environment";
  buildInputs = [
    pkgs.adoptopenjdk-hotspot-bin-8
    pkgs.maven
    pkgs.elasticsearch-oss
  ];
}
