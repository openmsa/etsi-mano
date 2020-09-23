 #!/bin/bash



for d in *; do
    if [ -d "${d}" ]; then
    	echo "${d}"
    	rm "${d}.zip" 2> /dev/null
    	pushd .
    	cd $d
    	zip -r "../ubi-${d}.csar" .
    	popd
    fi
done



