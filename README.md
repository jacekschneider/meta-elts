# meta-elts

## Overview

The `meta-elts` layer provides customizations and enhancements for the Yocto Project, tailored to specific project requirements. This layer includes various recipes and configurations to support the development of embedded Linux systems, with a focus on Raspberry Pi platforms.

## Dependencies

This layer depends on the following layers:

- **URI**: `git://git.openembedded.org/openembedded-core`
  - **Branch**: Specify the branch you're using (e.g., `master`, `dunfell`)
  - **Revision**: `HEAD`

- **URI**: `git://git.openembedded.org/meta-openembedded`
  - **Sub-layer**: Include `meta-oe`, `meta-python`, `meta-networking`, and `meta-multimedia`
  - **Branch**: Specify the branch you're using
  - **Revision**: `HEAD`

- **URI**: `https://github.com/raspberrypi/meta-raspberrypi.git`
  - **Branch**: Specify the branch you're using (e.g., `master`, `dunfell`)
  - **Revision**: `HEAD`

Ensure these layers are present in your Yocto build environment and are compatible with the `meta-elts` layer.

## Adding the meta-elts Layer to Your Build

To include the `meta-elts` layer in your build, follow these steps:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/jacekschneider/meta-elts.git
   ```

2. **Clone Dependencies**:

   Make sure to clone the required layers:
   - Meta-openembedded:
     ```bash
     git clone git://git.openembedded.org/meta-openembedded
     ```
   - Meta-raspberrypi:
     ```bash
     git clone https://github.com/raspberrypi/meta-raspberrypi.git
     ```

3. **Add the Layers to `bblayers.conf`**:

   Open your `conf/bblayers.conf` file and add the paths to the required layers, including `meta-elts`:

   ```bash
   BBLAYERS ?= " \
     ../poky/meta \
     ../poky/meta-poky \
     ../poky/meta-yocto-bsp \
     ../meta-openembedded/meta-oe \
     ../meta-openembedded/meta-python \
     ../meta-openembedded/meta-networking \
     ../meta-openembedded/meta-multimedia \
     ../meta-raspberrypi \
     ../meta-elts \
     "
   ```

## Usage

After adding the `meta-elts` layer to your build configuration, you can utilize the recipes and configurations it provides. Refer to the specific recipes within the layer for detailed usage instructions.

## License

This layer is licensed under the MIT License. See the `LICENSE` file for more details.

